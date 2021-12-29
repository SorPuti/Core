package br.com.puti.core.component.nms;

import org.apache.commons.lang.reflect.FieldUtils;

import java.lang.reflect.Field;

public class CoreField {

	private Object object;

	public CoreField(Object object) {
		this.object = object;
	}
	
	public Object getPrivateField(String fieldName) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			
			return field.get(object);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getPublicField(String fieldName) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			
			return field.get(object);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getStaticField(String fieldName) {
		try {
			Object field = FieldUtils.readDeclaredStaticField(object.getClass(), fieldName, true);
			
			return field;
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setPrivateField(String fieldName,Object value) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			
			field.set(object, value);
		} catch (SecurityException | IllegalArgumentException  | NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
