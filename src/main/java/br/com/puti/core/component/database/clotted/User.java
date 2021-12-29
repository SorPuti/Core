package br.com.puti.core.component.database.clotted;

import br.com.puti.core.Core;
import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.services.database.DataBaseServices;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class User extends DataClotted {

	private TreeMap<String, Object> current_search = new TreeMap<String, Object>();

	public User(String id, DataBase dataBase) {
		super(id, dataBase);

		this.changeConnection(dataBase);
	}
	@Override
	public DataClotted search(String... params) {
		if (getAk() == null)
			throw new NullPointerException("illegal access!");

		this.current_search.clear();
		List<DataBase> lista = this.getRelatedDataBase();
		for (int i = 0; i < lista.size(); i++) {
			DataBase arg0 = lista.get(i);
			for (String table : arg0.getTable()) {
				arg0.select(table);
				if (arg0.contains(getId())) {
					for (String param : params)
						arg0.getValue(getId(), param, (response, db, arg) -> {
							if (response.isProcessed()) {
								getAk().end(arg0, param, arg);
								current_search.put(param, arg);
							}
						});
					
				}

			}
			lista.remove(i);
		}
		return this;
	}


	@Override
	public DataClotted setAccessibility(DataClotted.DataAk<DataBase, Object> ak) {
		this.setAk(ak);
		return this;
	}

	@Override
	public DataClotted delete(DataAction action) {
		switch (action) {
		case ALL:
			this.getRelatedDataBase().forEach(arg0 -> {
				for (String table : arg0.getTable()) {
					arg0.select(table);
					arg0.delete(getId(), (response, db, arg) -> {
						if (!response.isProcessed())
							getMainDataBase().send(
									"&cDataClotted ERROR:&7 Não foi possível remover retirar os objetos da tabela "
											+ arg0.getSelectTable() + ".");
					});
				}
			});
			break;

		case ONE:
			DataBase arg0 = getDataBase();
			for (String table : arg0.getTable()) {
				arg0.select(table);
				arg0.delete(getId(), (response, db, arg) -> {
					if (!response.isProcessed())
						getMainDataBase()
								.send("&cDataClotted ERROR:&7 Não foi possível remover retirar os objetos da tabela "
										+ arg0.getSelectTable() + ".");
				});
			}
			break;

		default:
			break;
		}
		return this;
	}

	@Override
	public List<DataBase> getRelatedDataBase() {
		DataBaseServices service = Core.getPlugin(Core.class).get().getDataBaseLoader().getDataBaseServices();

		List<DataBase> dbs = new ArrayList<DataBase>();
		service.getAll().forEach(db -> {
			if (db != null)
				for (String table : db.getTable()) {
					db.select(table);
					if (db.contains(getId()))
						dbs.add(db);
				}
		});
		return dbs;
	}

	@Override
	public DataClotted setValue(String key, Object value) {
		this.getDataBase().setValue(getId(), key, value, (response, db, arg) -> {
			if (!response.isProcessed())
				this.getDataBase().send("&cDataClotted ERROR:&7 Não foi possivel alterar o valor da coluna " + key
						+ ", retorno: " + response.getMessage());
		});
		return this;
	}

	@Override
	public Object getValue(String key) {
		List<Object> value = new ArrayList<Object>();
		this.getDataBase().getValue(getId(), key, (response, db, arg) -> {
			if (!response.isProcessed())
				this.getDataBase().send("&cDataClotted ERROR:&7 Não foi possivel alterar o valor da coluna " + key
						+ ", retorno: " + response.getMessage());
			else
				value.add(arg);
		});
		return value.get(0);
	}

	@Override
	public DataClotted changeConnection(DataBase dataBase) {
		this.setDataBase(dataBase);
		return this;
	}

	@Override
	public TreeMap<String, Object> endResult() {
		return this.current_search;
	}

}
