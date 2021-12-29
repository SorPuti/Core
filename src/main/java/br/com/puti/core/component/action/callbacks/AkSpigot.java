package br.com.puti.core.component.action.callbacks;

import com.google.common.io.ByteArrayDataInput;

import br.com.puti.core.services.message.MessageServices;

public interface AkSpigot<T extends ByteArrayDataInput> {
	void callBack(T in, MessageServices author);
}
