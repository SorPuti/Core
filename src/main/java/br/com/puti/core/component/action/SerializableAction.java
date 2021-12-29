package br.com.puti.core.component.action;

import java.util.Map.Entry;

public interface SerializableAction<E extends Entry<String, String>> {
	void action(E paramsE);
}
