package br.com.puti.core.component.database.response;

public class DataBaseResponse {

	private DataBaseResponseType dataBaseResponseType;
	private Exception ex;

	public DataBaseResponse(DataBaseResponseType dataBaseResponseType, Exception ex) {
		this.dataBaseResponseType = dataBaseResponseType;
		this.ex = ex;
	}

	public boolean isProcessed() {
		return this.dataBaseResponseType == DataBaseResponseType.SUCESS;
	}
	
	public DataBaseResponseType getDataBaseResponse() {
		return this.dataBaseResponseType;
	}
	
	public int getCode() {
		return this.dataBaseResponseType.hasCode();
	}

	public void print() {
		if (ex != null)
			ex.printStackTrace();
	}
	
	public String getMessage() {
		if (ex != null)
			return ex.getMessage()+" - "+ex.getLocalizedMessage();
		else
			return "Nenhum retorno";
	}

}
