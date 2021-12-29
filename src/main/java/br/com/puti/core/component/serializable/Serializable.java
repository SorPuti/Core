package br.com.puti.core.component.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;


public class Serializable {

	private Object object;
	private String dataFolder;
	private String name;

	public Serializable(Object object, String name, String dataFolder, boolean container) {
		this.object = object;
		this.dataFolder = dataFolder + (container ? name + "/" : "");
		this.name = name;
		(new File(this.dataFolder)).mkdirs();
	}


	public void print() {
		Bukkit.getConsoleSender()
				.sendMessage(new String[] { "Name: " + name, "Path: " + this.dataFolder + name + ".abstract", "", });
	}

	public boolean serializable() {
		try {
			if (name == null)
				return false;

			FileOutputStream file = new FileOutputStream(this.dataFolder + name + ".abstract");
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(object);

			out.close();
			file.close();

			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public void deserializable(String name,Object target) throws IOException {
		try {

			Object model = null;
			FileInputStream file = new FileInputStream(this.dataFolder + name + ".abstract");
			ObjectInputStream in = new ObjectInputStream(file);

			model =  in.readObject();

			in.close();
			file.close();
			if (model == null)
				return;

			for (Field field : model.getClass().getDeclaredFields()) {
				try {
					Field f= target.getClass().getDeclaredField(field.getName());
					f.setAccessible(true);
					
					f.set(target, getField(field.getName(), model));
				} catch (Exception e) {
				}
		}

			return;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return;
		}
	}
	
	public Object getField(String field,Object a) {
		try {
			Field f = a.getClass().getDeclaredField(field);
			f.setAccessible(true);
			
			return f.get(a);
		} catch (Exception e) {
		return "[null]";
		}
	}
}
