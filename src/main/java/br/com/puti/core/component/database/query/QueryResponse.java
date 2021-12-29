package br.com.puti.core.component.database.query;

public enum QueryResponse {
    INTEGER("INTEGER", 0), DOUBLE("DOUBLE", 0.0d), FLOAT("FLOAT", 0.0f), STRING("VARCHAR", ""), DATE("DATE", "10/20"), BOOLEAN("BOOLEAN", false),
    CHAR("CHAR", '|'), ENUM("ENUM", QueryResponse.STRING), DATETIME("DATETIME", "10:00:00"), YEAR("YEAR", "20001"), BIT("BIT", (byte) 0), TIME("TIME", (long) 60), SERIAL("SERIAL", ""),
    TEXT("TEXT", "");

    private String type;
    private Object example;

    QueryResponse(String type, Object example) {
        this.type = type;
        this.example = example;
    }

    public String getType() {
        return type;
    }

    public QueryResponse setDefault(Object value) {
        this.example = value;
        return this;
    }

    public Object getExample() {
        return this.example;
    }
}
