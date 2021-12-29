package br.com.puti.core.component.database.clotted;

import br.com.puti.core.component.database.mysql.DataBase;

import java.util.List;
import java.util.TreeMap;


public abstract class DataClotted {

    private String id = null;
    private DataBase mainDataBase = null;
    private DataBase dataBase;
    private DataClotted.DataAk<DataBase, Object> ak;

    public DataClotted(String id, DataBase dataBase) {
        this.id = id;
        this.mainDataBase = dataBase;
    }

    public String getId() {
        return id;
    }

    public void setAk(DataAk<DataBase, Object> ak) {
        this.ak = ak;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public DataAk<DataBase, Object> getAk() {
        return ak;
    }

    public DataBase getMainDataBase() {
        return mainDataBase;
    }

    public abstract DataClotted search(String... params);

    public abstract DataClotted setAccessibility(DataClotted.DataAk<DataBase, Object> ak);

    public abstract TreeMap<String, Object> endResult();

    @Deprecated
    public abstract DataClotted setValue(String key, Object value);

    @Deprecated
    public abstract Object getValue(String key);

    public abstract DataClotted delete(DataAction action);

    public abstract DataClotted changeConnection(DataBase dataBase);

    public abstract List<DataBase> getRelatedDataBase();

    public final void close() {
        this.mainDataBase.closeDataClotted(this);
    }

    protected void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public enum DataAction {
        ALL, ONE, NONE;
    }

    public interface DataAk<E extends DataBase, T> {
        void end(E dataBase, String key, T value);
    }

}