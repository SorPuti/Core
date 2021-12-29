package br.com.puti.core.component.database.compressed.serialize;

import br.com.puti.core.component.database.compressed.Data;
import br.com.puti.core.component.database.compressed.reflections.DataReflected;

import java.lang.reflect.Field;

public class Clazz {

    private Object clazz;
    private DataReflected reflected;

    public Clazz(Object clazz) {
        this.clazz = clazz;
        this.reflected = new DataReflected();
        this.generate();
    }

    private Object getField(String fieldName) {
        try {
            Field f = clazz.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(clazz);
        } catch (Exception ex) {
            return null;
        }
    }

    private void generate() {
        try {
            Field[] values = clazz.getClass().getDeclaredFields();

            for (Field value : values) {
                String colum = value.getName();
                Object object = getField(colum);
                Data data = new Data(colum, object);

                this.reflected.addField(data);
            }

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public DataReflected getReflected() {
        return reflected;
    }

}
