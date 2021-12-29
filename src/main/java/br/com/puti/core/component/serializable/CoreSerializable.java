package br.com.puti.core.component.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import br.com.puti.core.Core;

public abstract class CoreSerializable {

	public Serializable getSerializable(Object object, String name) {
		return new Serializable(object, name, Core.getPlugin(Core.class).getDataFolder() + "/serials/", true);
	}

	public Serializable getSerializable(Object object, String name, String dataFolder) {
		return new Serializable(object, name, dataFolder, false);
	}

	public Serializable getSerializable(Object object, String name, String dataFolder, boolean container) {
		return new Serializable(object, name, dataFolder, container);
	}

	public void SerializableList(List<Object> list, String name) {
		try {
			FileOutputStream file = new FileOutputStream(
					Core.getPlugin(Core.class).getDataFolder() + "/crypto/" + name + ".abstract");
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(list);

			out.close();
			file.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> DeserializableList(String name) {
		try {
			FileInputStream file = new FileInputStream(
					Core.getPlugin(Core.class).getDataFolder() + "/crypto/" + name + ".abstract");
			ObjectInputStream in = new ObjectInputStream(file);

			List<Object> model = (List<Object>) in.readObject();

			in.close();
			file.close();
			return  model;
		} catch (ClassNotFoundException | IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
