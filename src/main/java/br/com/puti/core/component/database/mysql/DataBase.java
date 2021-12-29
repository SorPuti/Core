package br.com.puti.core.component.database.mysql;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import br.com.puti.core.component.database.clotted.DataClotted;
import org.bukkit.Bukkit;

import br.com.puti.core.Core;
import br.com.puti.core.component.action.CoreAction;
import br.com.puti.core.component.database.query.QueryKey;

/**
 * @author SrPuti_
 */

public abstract class DataBase {

	private String url = "";
	
	public DataBase(String host, String database, int port) {
		this.url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoreconnect=true";
	}

	public String getURL() {
		return this.url;
	}

	public void send(String... messages) {
		for (String line : messages) {
			Bukkit.getConsoleSender().sendMessage("§e[Core/DataBase] §f" + line.replace("&", "§"));
		}
	}
	
	public abstract void closeDataClotted(DataClotted arg0);
	
	public abstract boolean InAction(String arg0);
	
	public abstract DataClotted createDataClotted(String arg0);
	
	public abstract DataClotted getDataClotted(String arg0);

	/**
	 * @return Conexão atual
	 */

	public abstract Connection getConnection();

	/**
	 * @params new Connection
	 */

	public abstract void setConnection(Connection connection);

	/**
	 * 
	 * Crie um tabela
	 * 
	 * @param table - Insert new table
	 * @param keys  - Insert new collum in table
	 * @return Resultado ta operação
	 */

	public abstract <T> void createTable(CoreAction<Object> action, String table, QueryKey... keys);

	
	public abstract void createTable(String table, QueryKey... keys);

	/**
	 * 
	 * Resgate todas as tabelas criadas.
	 * 
	 * @return Lista de tabelas
	 */

	public abstract List<String> getTable();

	
	public abstract List<QueryKey> getColluns();
	
	/**
	 * 
	 * Resgate todas as colunas criadas.
	 * 
	 * @return Lista de colunas
	 */

	public abstract TreeMap<String, List<QueryKey>> getAks();

	/**
	 * 
	 * Resgate a tabela selicionada
	 * 
	 * @return Nome da tabela
	 */

	public abstract String getSelectTable();

	/**
	 * 
	 * ID que referência a esta classe
	 * 
	 * @return ref ID
	 */

	public abstract String getID();

	/**
	 * Fechar conexão
	 */

	public abstract void close();

	/**
	 * 
	 * Check se existe alguma conexão existente
	 * 
	 * @return true or false
	 */

	public abstract boolean isConnected();

	/**
	 * 
	 * Alterar valores da tabela
	 * 
	 * @param <T>
	 * 
	 * @return Resultado da operação
	 */

	public abstract <T> void setValue(String id, String key, Object value, CoreAction<Object> action);

	
	public abstract void setValue(String id, String key, Object value);

	/**
	 * 
	 * Reconstruir todas as tabelas criadas
	 * 
	 * @return Resultado da operação
	 */

	public abstract <T> void reset();

	/**
	 * 
	 * Criar um novo objecto dentro da tabela de acordo do {@value}
	 * 
	 * @param id
	 * 
	 * @return Resultado da operação
	 */

	public abstract <T> void create(String id, CoreAction<Object> action);

	
	public abstract void create(String id);

	/**
	 * 
	 * Deleta um objecto dentro da tabela de acordo do {@value}
	 * 
	 * @param id
	 * 
	 * @return Resultado da operação
	 */

	public abstract <T> void delete(String id, CoreAction<Object> action);

	
	public abstract void delete(String id);

	/**
	 * 
	 * Deleta uma coluna desta tabela
	 * 
	 * @param id
	 * @param keys...
	 * 
	 * @return Resultado da operação
	 */

	public abstract <T> void deleteKey(CoreAction<Object> action,String id, QueryKey... keys);

	
	public abstract void deleteKey(String id, QueryKey... keys);

	/**
	 * 
	 * Seleciona diferentes tabelas de trabalho
	 * 
	 * 
	 * @param table - Paramentro de substituição
	 * 
	 * @return Resultado da operação
	 */

	public abstract <T> void select(String table, CoreAction<Object> action);

	
	public abstract void select(String table);

	/**
	 * 
	 * Verifica se existe algum objecto com um idenficador
	 * 
	 * @param id - Paramentro comparativo
	 * 
	 * @return Resultado da operação
	 */

	public abstract boolean contains(String id);

	/**
	 * 
	 * Busca informações dentro da tabela
	 * 
	 * @param id  - Paramentro comparativo
	 * @param key - valor que deseja pegar dentro da tabela
	 * 
	 * @return Resultado da busca
	 */

	public abstract <T> void getValue(String id, String key, CoreAction<Object> action);

	
	public abstract Object getValue(String id, String key);

	/**
	 * 
	 * Busca informações dentro da tabela
	 * 
	 * @param id   - Paramentro comparativo
	 * @param key  - valor que deseja pegar dentro da tabela
	 * @param key2 - valor que deseja pegar dentro da tabela
	 * 
	 * @return Resultado da busca
	 */

	public abstract <T> void multGet(String id, String key, String key2, CoreAction<Object> action);

	public abstract Object[] multGet(String id, String key, String key2);

	/**
	 * 
	 * Busca uma lista de informações em ordem decrecente
	 * 
	 * @serialData
	 * 
	 * @param id  - Paramentro comparativo
	 * @param key - Sorty by {@value}
	 * 
	 * @return Listagem de busca
	 */

	public abstract HashMap<Integer, Object> getMap(String key);

	/**
	 * 
	 * Pegar todos os objectos dentro da tabela
	 * 
	 * @return Listagem de busca
	 */

	public abstract List<Object> getAll();

	/**
	 * 
	 * Busca uma lista de informações em ordem decrecente
	 * 
	 * @serialData
	 * 
	 * @param id  - Paramentro comparativo
	 * @param key - Sorty by {@value}
	 * 
	 * @return Listagem de busca
	 */

	public abstract TreeMap<Integer, Object> getTreeMap(String key);

	public void print() {
		Core.getPlugin(Core.class).log(" &eTable info", "", " &fName: &7" + getID(),
				" &fSelect Table: &7" + getSelectTable(), " &fURL database: &7" + getURL(),
				" &fObject Loaders: &7" + getAll().size(), " &fCreated Tables: &7" + getTable().size(), "");
	}

}
