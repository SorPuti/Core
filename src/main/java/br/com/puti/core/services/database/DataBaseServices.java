package br.com.puti.core.services.database;

import br.com.puti.core.Core;
import br.com.puti.core.component.database.exceptions.DataBaseNotFoundException;
import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.component.model.CorePlayer;
import br.com.puti.core.loader.DataBaseLoader;

import java.util.ArrayList;
import java.util.List;

public class DataBaseServices {

    protected DataBaseLoader loader;
    protected List<DataBase> dataBases;

    public DataBaseServices(DataBaseLoader loader) {
        if (loader.getDataBaseServices() == null) {
            this.loader = loader;
            this.dataBases = new ArrayList<DataBase>();
        } else {
            throw new NullPointerException("Esta classe nao pode ser instanciada.(DataBaseServices.class ?:?)");
        }
    }

    public void register(DataBase dataBase, boolean autoConnect) {
        if (autoConnect)
            dataBase.getConnection();
        else if (dataBases.get(0) != null)
            dataBase.setConnection(dataBases.get(0).getConnection());

        this.dataBases.add(dataBase);
        Core.getPlugin(Core.class).log("Banco de dados `&7" + dataBase.getID() + "&f` carregado");
    }

    public void unregister(DataBase dataBase) {
        this.dataBases.remove(dataBase);
    }

    public void unregisterAll() {
        for (DataBase base : dataBases)
            if (base.isConnected())
                base.close();

        this.dataBases.clear();
    }

    public DataBase get(String table) {
        for (DataBase base : dataBases)
            if (base.getID().equalsIgnoreCase(table))
                return base;

        throw new DataBaseNotFoundException(table);
    }

    public void cascadeRemove(CorePlayer corePlayer) {
        for (DataBase database : dataBases)
            if (database.contains(corePlayer.getName()))
                database.delete(corePlayer.getName(), null);
            else if (database.contains(corePlayer.getUUID().toString()))
                database.delete(corePlayer.getUUID().toString(), null);
    }

    public void cascadeSet(CorePlayer corePlayer, String key, String value) {
        for (DataBase database : dataBases)
            if (database.contains(corePlayer.getName()))
                database.setValue(corePlayer.getName(), key, value, null);
            else if (database.contains(corePlayer.getUUID().toString()))
                database.setValue(corePlayer.getUUID().toString(), key, value, null);
    }

    public DataBase[] cascadeSearch(String arg0) {
        List<DataBase> maps = new ArrayList<>();

        for (DataBase database : dataBases)
            if (database.getAks().containsKey(arg0))
                maps.add(database);

        return maps.toArray(new DataBase[]{});
    }

    public void resetAll() {
        for (DataBase database : dataBases) {
            database.reset();
        }
    }

    public void reset(String name) {
        DataBase database = get(name);

        database.reset();
    }

    public void checkAndCreate(String id) {
        dataBases.forEach(database -> {
            for (String table : database.getTable()) {
                database.select(table, null);
                if (!database.contains(id)) {
                    database.create(id, null);
                } else {
                }
            }
        });
    }

    public void regenConnections() {
        dataBases.forEach(database -> {
            if (database.getConnection() == null)
                Core.getManager().send("&c&nERROR&c Não teve nenhum resultado ao tenta reconectar a conexão '" + database.getID() + "'.");
        });
    }

    public int size() {
        return this.dataBases.size();
    }

    public List<DataBase> getAll() {
        return this.dataBases;
    }

    public int getDeconnected() {
        int amount = 0;
        for (DataBase db : this.dataBases)
            if (!db.isConnected())
                amount++;

        return amount;
    }

}
