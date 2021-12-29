package br.com.puti.core.component.database.compressed;


public class Data {

    private String colum;
    private Object value;

    public Data(String colum, Object value) {
        this.colum = colum;
        this.value = value;
    }

    public Data(String colum) {
        this.colum = colum;
        this.value = null;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getColum() {
        return colum;
    }

    public String toSerializa() {
        return "json";
    }
}
