package br.com.puti.core.component.action.callbacks;

import com.google.common.io.ByteArrayDataInput;

import br.com.puti.core.services.message.MessageServices;

public class CallBack implements AkSpigot<ByteArrayDataInput> {

	private AkSpigot<ByteArrayDataInput> action;
	
	public String data;

	@Override
	public void callBack(ByteArrayDataInput in, MessageServices author) {
		data = in.readUTF();
		this.action.callBack(in, author);

	}

	public <T> CallBack onCallBack(AkSpigot<ByteArrayDataInput> action) {
		this.action = action;
		return this;
	}
	
	public String getData() {
		return this.data;
	}
	


}
