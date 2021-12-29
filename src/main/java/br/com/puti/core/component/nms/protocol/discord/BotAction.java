package br.com.puti.core.component.nms.protocol.discord;

public class BotAction<E>{

	private E paramsE;
	
	public BotAction(E paramsE) {
		this.paramsE = paramsE;
	}
	
	public E getBotAction() {
		return paramsE;
	}


}
