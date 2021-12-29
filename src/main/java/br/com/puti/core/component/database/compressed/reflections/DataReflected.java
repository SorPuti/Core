package br.com.puti.core.component.database.compressed.reflections;

import br.com.puti.core.component.database.compressed.Data;

import java.util.HashMap;

public class DataReflected {

    public HashMap<String, Data> fields = new HashMap<>();


    public HashMap<String, Data> getFields() {
        return fields;
    }

    public void addField(Data fieldData){
        fields.put(fieldData.getColum(),fieldData);
    }

    public Class<? extends DataReflected> getClazz(){
        return getClass();
    }


}
