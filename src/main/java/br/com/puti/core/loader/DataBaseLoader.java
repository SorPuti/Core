package br.com.puti.core.loader;

import java.util.ArrayList;
import java.util.List;

import br.com.puti.core.component.config.FileManager;
import br.com.puti.core.component.database.mysql.Mysql;
import br.com.puti.core.component.database.exceptions.DataBaseUndefinedException;
import br.com.puti.core.services.database.DataBaseServices;

public class DataBaseLoader {

	protected CoreLoader plugin;
	private DataBaseServices dataBaseServices = null;
	private FileManager fileManager = null;

	private List<String> DEFAULTS_DATABASE = new ArrayList<String>();

	public DataBaseLoader(CoreLoader plugin) {
		if (plugin.getDataBaseLoader() == null)
			this.plugin = plugin;
		else
			throw new NullPointerException("Esta classe nao pode ser instanciada.(DataBaseLoader.class ?:?)");

	}

	public void loader() {
		this.fileManager = new FileManager("core.yml");
		if (!(this.fileManager.existeConfig()))
			this.fileManager.saveDefaultConfig();
		
		try {
			this.DEFAULTS_DATABASE.add("database_core");

			this.dataBaseServices =  new DataBaseServices(this);
			
			String username = (String) fileManager.get("DataBase.username");
			String password = (String) fileManager.get("DataBase.password");
			String host = (String) fileManager.get("DataBase.host");
			String database = (String) fileManager.get("DataBase.database");

			for (String id : DEFAULTS_DATABASE)
				try {
					this.dataBaseServices.register(new Mysql(username, password, host, database, id, 3306), true);
				} catch (ClassNotFoundException e) {
					throw new DataBaseUndefinedException(e.getMessage());
				}
		} finally {
		}

	}

	public List<String> DEFAULTS_DATABASES() {
		return this.DEFAULTS_DATABASE;
	}
	
	public FileManager geFile() {
		return this.fileManager;
	}

	public DataBaseServices getDataBaseServices() {
		return this.dataBaseServices;
	}
	
	
}
