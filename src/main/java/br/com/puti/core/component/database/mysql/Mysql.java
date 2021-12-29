package br.com.puti.core.component.database.mysql;

import br.com.puti.core.Core;
import br.com.puti.core.component.action.CoreAction;
import br.com.puti.core.component.cryptography.model.Crypto;
import br.com.puti.core.component.database.clotted.DataClotted;
import br.com.puti.core.component.database.clotted.User;
import br.com.puti.core.component.database.exceptions.DataBaseKeyNotFoundExeception;
import br.com.puti.core.component.database.exceptions.DataBaseUndefinedException;
import br.com.puti.core.component.database.query.QueryKey;
import br.com.puti.core.component.database.response.DataBaseResponse;
import br.com.puti.core.component.database.response.DataBaseResponseType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Mysql extends DataBase {

	protected final String username;
	protected final String host;
	protected final String password;
	protected final List<String> tables = new ArrayList<>();
	protected String table;

	protected final String id;
	protected final int port;
	protected HikariDataSource dataSource = null;
	protected final TreeMap<String, List<QueryKey>> aks = new TreeMap<String, List<QueryKey>>();
	private TreeMap<String, DataClotted> dataCascade = new TreeMap<String, DataClotted>();

	private Connection connection = null;

	public Mysql(String id, int port) {
		super((String) Core.getMainFile().get("DataBase.host"), (String) Core.getMainFile().get("DataBase.database"),
				port);

		this.username = (String) Core.getMainFile().get("DataBase.username");
		this.password = (String) Core.getMainFile().get("DataBase.password");
		this.host = (String) Core.getMainFile().get("DataBase.host");
		this.table = "";
		this.port = port;
		this.id = id;
	}

	public Mysql(String username, String password, String host, String database, String id, int port)
			throws ClassNotFoundException {
		super(host, database, port);
		this.username = username;
		this.host = host;
		this.password = password;
		this.table = "";
		this.id = id;
		this.port = port;
	}

	public Mysql(String username, String password, String host, String id, int port) throws ClassNotFoundException {
		super(host, "test", port);
		this.username = username;
		this.host = host;
		this.password = password;
		this.table = "";
		this.id = id;
		this.port = port;
	}

	@Override
	public final DataClotted getDataClotted(String arg0) {
		if (!dataCascade.containsKey(arg0))
			return null;

		return dataCascade.get(arg0);
	}

	@Override
	public final DataClotted createDataClotted(String arg0) {
		if (InAction(arg0))
			return this.getDataClotted(arg0);

		User user = new User(arg0, this);
		dataCascade.put(arg0, user);
		return user;
	}

	@Override
	public final boolean InAction(String arg0) {
		return dataCascade.containsKey(arg0);
	}

	@Override
	public final void closeDataClotted(DataClotted arg0) {
		if (dataCascade.containsKey(arg0.getId()))
			dataCascade.remove(arg0.getId(), arg0);

		arg0 = null;
	}

	private QueryKey[] toKey(List<QueryKey> keys) {
		QueryKey[] o = (QueryKey[]) keys.toArray();
		return o;
	}

	private QueryKey getByCollum(String collum) {
		List<QueryKey> list = aks.get(table);
		for (QueryKey key : list)
			if (key.key.equals(collum))
				return key;

		return null;
	}

	@Override
	public Connection getConnection() {
		if (!isConnected()) {
			try {
				this.connection = setupConfig();
			} catch (SQLException e) {
				throw new DataBaseUndefinedException(e.getMessage());
			}
		}

		return this.connection;
	}

	@Override
	public synchronized <T> void select(String table, CoreAction<Object> action) {
		if (tables.contains(table)) {
			this.table = table;
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this, table);
		} else {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.FAILED, null), this, table);
			throw new DataBaseKeyNotFoundExeception(table);
		}
	}

	@Override
	public synchronized void select(String table) {
		if (tables.contains(table)) {
			this.table = table;
		} else {
			throw new DataBaseKeyNotFoundExeception(table);
		}
	}

	@Override
	public <T> void createTable(CoreAction<Object> action, String table, QueryKey... keys) {
		try {
			this.table = table;
			this.tables.add(table);
			String query = "CREATE TABLE IF NOT EXISTS `" + table
					+ "`(`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(100) NULL, ";

			List<QueryKey> list = new ArrayList<>();
			for (int i = 0; i < keys.length; i++) {
				query = query.concat(keys[i].serialize(i));
				list.add(keys[i]);
			}
			aks.put(table, list);

			String end = " NULL, PRIMARY KEY (`id`));";

			PreparedStatement statement = this.connection.prepareStatement(query + end);
			statement.execute();
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this, table);
		} catch (Exception e) {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, e), this, table);
		}
	}

	@Override
	public void createTable(String table, QueryKey... keys) {
		try {
			this.table = table;
			this.tables.add(table);
			String query = "CREATE TABLE IF NOT EXISTS `" + table
					+ "`(`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(100) NULL, ";

			List<QueryKey> list = new ArrayList<>();
			for (int i = 0; i < keys.length; i++) {
				query = query.concat(keys[i].serialize(i));
				list.add(keys[i]);
			}
			aks.put(table, list);

			String end = " NULL, PRIMARY KEY (`id`));";

			PreparedStatement statement = this.connection.prepareStatement(query + end);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getTable() {
		return this.tables;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isConnected() {
		try {
			boolean valid = (connection.isClosed() == false);

			if (valid == true)
				try {
					this.getAll();
				}catch (Exception ex){
					return false;
				}


			return valid;
		}catch (Exception ex){
			return false;
		}
	}

	@Override
	public <T> void setValue(String id, String key, Object value, CoreAction<Object> action) {
		try {
			QueryKey q = getByCollum(key);
			if (q != null && q.isEncrypto())
				value = new Crypto(value.toString()).encrypt();

			String query = "UPDATE `" + table + "` SET `" + key + "`= '" + value + "' WHERE `name` = '" + id + "'";

			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.executeUpdate();
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this, table);
		} catch (Exception e) {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.FAILED, e), this, table);
		}
	}

	@Override
	public void setValue(String id, String key, Object value) {
		try {
			QueryKey q = getByCollum(key);
			if (q != null && q.isEncrypto())
				value = new Crypto(value.toString()).encrypt();

			String query = "UPDATE `" + table + "` SET `" + key + "`= '" + value + "' WHERE `name` = '" + id + "'";

			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<QueryKey> getColluns() {
		List<QueryKey> list = aks.get(table);
		return list;
	}

	@Override
	public <T> void create(String id, CoreAction<Object> action) {
		try {
			if (contains(id))
				if (action != null)
					action.response(new DataBaseResponse(DataBaseResponseType.UNKNOWN, null), this, null);

			String query = "INSERT INTO `" + table + "`(`name`, ";

			for (int i = 0; i < aks.get(table).size(); i++)
				query = query.concat(aks.get(table).get(i).simpleSerialize(i));

			String end = query + ") VALUES (?, ";

			for (int i = 0; i < aks.get(table).size(); i++)
				end = end.concat("?" + (i == (aks.get(table).size() - 1) ? "" : ", "));

			String commandSql = end + ")";

			PreparedStatement stm = this.connection.prepareStatement(commandSql);
			stm.setString(1, id);
			int i = 2;
			for (QueryKey key : aks.get(table)) {
				stm.setObject(i, key.queryResponse.getExample());
				i++;
			}

			stm.execute();
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this, null);
		} catch (SQLException e) {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.FAILED, e), this, null);
		}
	}

	@Override
	public void create(String id) {
		try {
			if (contains(id))
				return;

			String query = "INSERT INTO `" + table + "`(`name`, ";

			for (int i = 0; i < aks.get(table).size(); i++)
				query = query.concat(aks.get(table).get(i).simpleSerialize(i));

			String end = query + ") VALUES (?, ";

			for (int i = 0; i < aks.get(table).size(); i++)
				end = end.concat("?" + (i == (aks.get(table).size() - 1) ? "" : ", "));

			String commandSql = end + ")";

			PreparedStatement stm = this.connection.prepareStatement(commandSql);
			stm.setString(1, id);
			int i = 2;
			for (QueryKey key : aks.get(table)) {
				stm.setObject(i, key.queryResponse.getExample());
				i++;
			}

			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> void getValue(String id, String key, CoreAction<Object> action) {
		try {
			PreparedStatement stm = this.connection.prepareStatement("SELECT * FROM `" + table + "` WHERE `name` = ?");
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next())
				if (action != null)
					action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this, rs.getString(key));

		} catch (SQLException e) {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.FAILED, e), this, null);
		}
	}

	@Override
	public Object getValue(String id, String key) {
		try {
			PreparedStatement stm = this.connection.prepareStatement("SELECT * FROM `" + table + "` WHERE `name` = ?");
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next())
				return rs.getString(key);

			return "";
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public <T> void multGet(String id, String key, String key2, CoreAction<Object> action) {
		try {
			PreparedStatement stm = this.connection.prepareStatement("SELECT * FROM `" + table + "` WHERE `name` = ?");
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next())
				if (action != null) {

					Object value1 = "";
					Object value2 = "";
					try {
						value1 = rs.getObject(key);
					} catch (Exception e) {
					}

					try {
						value2 = rs.getObject(key2);
					} catch (Exception e) {
					}
					action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this,
							new Object[] { value1, value2 });
				}

		} catch (SQLException e) {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.FAILED, null), this, new Object[] {null,null});
		}
	}

	@Override
	public Object[] multGet(String id, String key, String key2) {
		try {
			PreparedStatement stm = this.connection.prepareStatement("SELECT * FROM `" + table + "` WHERE `name` = ?");
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				Object value1 = null;
				Object value2 = null;
				try {
					value1 = rs.getObject(key);
				} catch (Exception e) {
				}

				try {
					value2 = rs.getObject(key2);
				} catch (Exception e) {
				}
				return new Object[] { value1, value2 };
			}

			return new Object[] { null, null };
		} catch (SQLException e) {
			return new Object[] { null, null };
		}
	}

	@Override
	public List<Object> getAll() {
		List<Object> list = new ArrayList<Object>();

		try {
			PreparedStatement stm = this.connection
					.prepareStatement("SELECT * FROM `" + table + "` ORDER BY `id` DESC");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("name"));
			}
		} catch (SQLException e) {
		}
		return list;
	}

	@Override
	public HashMap<Integer, Object> getMap(String key) {
		HashMap<Integer, Object> list = new HashMap<Integer, Object>();

		try {
			PreparedStatement stm = this.connection
					.prepareStatement("SELECT * FROM `" + table + "` ORDER BY `" + key + "` DESC");
			ResultSet rs = stm.executeQuery();
			int i = 1;
			while (rs.next()) {
				if (i <= 10)
					list.put(i, rs.getString("name"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean contains(String id) {
		synchronized (id) {
			for (Object currentId : getAll()) {
				if (id.equalsIgnoreCase(currentId.toString()))
					return true;
			}
			return false;
		}
	}

	@Override
	public TreeMap<Integer, Object> getTreeMap(String key) {
		TreeMap<Integer, Object> list = new TreeMap<Integer, Object>();

		try {
			PreparedStatement stm = this.connection
					.prepareStatement("SELECT * FROM `" + table + "` ORDER BY `" + key + "` DESC");
			ResultSet rs = stm.executeQuery();
			int i = 1;
			while (rs.next()) {
				if (i <= 10)
					list.put(i, rs.getString("name"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public <T> void delete(String id, CoreAction<Object> action) {
		try {
			PreparedStatement stm = this.connection.prepareStatement("DELETE FROM `" + table + "` WHERE `name` = ?");
			stm.setString(1, id);
			stm.executeUpdate();
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this, null);
		} catch (SQLException e) {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.FAILED, e), this, null);
		}
	}

	@Override
	public void delete(String id) {
		try {
			PreparedStatement stm = this.connection.prepareStatement("DELETE FROM `" + table + "` WHERE `name` = ?");
			stm.setString(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> void deleteKey(CoreAction<Object> action, String id, QueryKey... keys) {
		try {
			for (QueryKey key : keys) {
				String query = "ALTER TABLE `" + table + "` DROP COLUMN " + key.key;
				PreparedStatement stm = this.connection.prepareStatement(query);
				stm.executeUpdate();
			}

			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.SUCESS, null), this, null);
		} catch (SQLException e) {
			if (action != null)
				action.response(new DataBaseResponse(DataBaseResponseType.FAILED, e), this, null);
		}
	}

	@Override
	public void deleteKey(String id, QueryKey... keys) {
		try {
			for (QueryKey key : keys) {
				String query = "ALTER TABLE `" + table + "` DROP COLUMN " + key.key;
				PreparedStatement stm = this.connection.prepareStatement(query);
				stm.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection setupConfig() throws SQLException {

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(getURL());
		config.setMaximumPoolSize(20);

		config.setUsername(username);
		config.setPassword(password);

		config.addDataSourceProperty("useSSL", "false");
		config.addDataSourceProperty("autoReconnect", "true");
		config.addDataSourceProperty("charsetEncoding", "utf-8");
		config.addDataSourceProperty("encoding", "UTF-8");
		config.addDataSourceProperty("useUnicode", "true");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("useServerPrepStmts", "true");

		dataSource = new HikariDataSource(config);

		return dataSource.getConnection();
	}

	public HikariDataSource getDataSource() {
		return dataSource;
	}

	@Override
	public String getSelectTable() {
		return table;
	}

	@Override
	public TreeMap<String, List<QueryKey>> getAks() {
		return this.aks;
	}

	@Override
	public void reset() {
		try {
			for (String table : tables) {
				PreparedStatement stm = this.connection.prepareStatement("DROP TABLE IF EXISTS " + table);
				stm.execute();

				createTable((response, databas, data) -> {

				}, table, toKey(aks.get(table)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
