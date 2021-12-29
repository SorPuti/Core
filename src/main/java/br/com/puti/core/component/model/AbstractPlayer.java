package br.com.puti.core.component.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.Player;

import br.com.puti.core.Core;
import br.com.puti.core.component.cryptography.model.Crypto;
import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.loader.CoreLoader;
import br.com.puti.core.services.accounts.AccountServices;

public abstract class AbstractPlayer<E> {

	private final String name;
	private final int ordinal;
	private final HashMap<String, Field> _fields;
	protected AccountServices _$cc0unt = null;

	private E clazz;
	protected DataBase dataBase = null;

	protected AbstractPlayer(String name, int ordinal) {
		this.name = name;
		this.ordinal = (new Random()).nextInt() + ordinal;
		this.clazz = null;
		this._fields = new HashMap<String, Field>();
		try {
			CoreLoader loader = Core.getPlugin(Core.class).get();
			this.dataBase = loader.getAccountServices().getDataBase();
			this._$cc0unt = loader.getAccountServices();
		} catch (Exception e) {
		}
	}

	protected final void Super(E clazz) {
		this.clazz = clazz;
	}

	public final void Super(Field... args) {
		if (clazz == null)
			return;

		for (Field f : args)
			_fields.put(f.getName(), f);
	}

	public final Object getField(String name) {
		try {
			if (clazz == null)
				return null;

			if (!_fields.containsKey(name))
				return null;
			Field a = this._fields.get(name);
			a.setAccessible(true);

			Object value = a.get(clazz);
			return value;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
	}

	public final boolean setField(String name, Object arg) {
		try {
			if (clazz == null)
				return false;

			if (!_fields.containsKey(name))
				return false;
			Field a = this._fields.get(name);
			a.setAccessible(true);
			if (isDouble(arg.toString()))
				a.set(clazz, Double.parseDouble(arg.toString()));
			else if (isInt(arg.toString()))
				a.set(clazz, Integer.parseInt(arg.toString()));
			else if (isBool(arg.toString()))
				a.set(clazz, Boolean.parseBoolean(arg.toString()));
			else
				a.set(clazz, arg);
			return true;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return false;
		}
	}

	public final String name() {
		return "CorePlayer{" + name + "}";
	}

	private boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isBool(String value) {
		try {
			Boolean.parseBoolean(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public final String getName() {
		return name;
	}

	public final int ordinal() {
		return ordinal;
	}

	public String toString() {
		return name;
	}

	public final boolean equals(Object other) {
		return this == other || this.getClass() == other.getClass();
	}

	public final boolean equals(String other) {
		return this.getName().equals(other);
	}

	public final boolean equals(Player other) {
		return this.getName().equals(other.getName());
	}

	public final int hashCode() {
		return super.hashCode();
	}

	public final Object hashCore() {
		return new Crypto(this.encrypto()).encrypt();
	}

	protected final String encrypto() {
		String v$l$ues = "";
		for (Field f : getClass().getDeclaredFields()) {
			try {
				f.setAccessible(true);
				v$l$ues = v$l$ues.concat((v$l$ues.isEmpty() ? "" : ",") + f.getName() + ":" + f.get(clazz));
			} catch (IllegalArgumentException | IllegalAccessException e) {
			}
		}
		return v$l$ues;
	}

	protected final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public final void clone(String arg0) {
		if (arg0.isEmpty())
			return;
		Crypto crypto = new Crypto(arg0);
		try {
			String[] args = crypto.decrypt(false).split(",");
			for (String arg1 : args) {
				String[] map = arg1.split(":");
				String key = map[0];
				String value = map[1];

				setField(key, value);
			}
		} catch (Exception e) {
		}
	}
	
	public final int compareTo(E o) {
		AbstractPlayer<?> other = (AbstractPlayer<?>) o;
		AbstractPlayer<E> self = this;
		if (self.getClass() != other.getClass() && self.getDeclaringClass() != other.getDeclaringClass())
			throw new ClassCastException();

		return self.ordinal - other.ordinal;
	}

	@SuppressWarnings("unchecked")
	protected final Class<E> getDeclaringClass() {
		Class<?> clazz = getClass();
		Class<?> zuper = clazz.getSuperclass();
		return (zuper == Enum.class) ? (Class<E>) clazz : (Class<E>) zuper;
	}

	protected final void finalize() {
	}
}
