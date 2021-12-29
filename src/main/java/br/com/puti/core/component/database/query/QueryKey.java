package br.com.puti.core.component.database.query;

public final class QueryKey {

	public String key, type;
	public QueryResponse queryResponse;
	public int amount = 0;
	private boolean encrypto = false;

	public QueryKey(String key, String type, int amount) {
		this.key = key;
		this.type = type;
		this.amount = amount;
		this.queryResponse = QueryResponse.valueOf(type);
	}

	public QueryKey(String key, QueryResponse type, int amount) {
		this.key = key;
		this.type = type.getType();
		this.queryResponse = type;
		this.amount = amount;
	}

	public QueryKey(String key, QueryResponse type, Object object) {
		this.key = key;
		this.type = type.getType();
		this.queryResponse = type;
		this.amount = 0;
		this.queryResponse.setDefault(object);
	}

	public QueryKey(String key, QueryResponse type, int amount, Object object) {
		this.key = key;
		this.type = type.getType();
		this.queryResponse = type;
		this.amount = amount;
		this.queryResponse.setDefault(object);
	}

	public QueryKey encrypto() {
		this.encrypto = true;
		return this;
	}

	public boolean isEncrypto() {
		return this.encrypto;
	}

	public String serialize(int value) {
		return (value >= 1 ? ", " : "") + " `" + key + "` " + queryResponse.getType()
				+ (amount > 0 ? "(" + amount + ")" : "") + " NULL";
	}

	public String simpleSerialize(int value) {
		return (value >= 1 ? ", " : "") + "`" + this.key + "`";
	}

}
