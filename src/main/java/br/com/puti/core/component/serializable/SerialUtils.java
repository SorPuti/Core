package br.com.puti.core.component.serializable;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class SerialUtils<E> {


    public String serialize(Object clazz) {
        String code = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(clazz);
            bukkitObjectOutputStream.flush();

            byte[] serializedObject = byteArrayOutputStream.toByteArray();

            code = Base64.getEncoder().encodeToString(serializedObject);
        } finally {
            return code;
        }

    }

    public E deserialize(String code)  {
        try {
            System.out.println("code = " + code);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(code));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);

            return (E) bukkitObjectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
