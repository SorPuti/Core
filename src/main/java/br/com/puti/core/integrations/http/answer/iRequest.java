package br.com.puti.core.integrations.http.answer;

import br.com.puti.core.integrations.http.answer.in.Request;

public class iRequest implements Request {

    protected String token;
    protected Object value;

    public iRequest(String token, Object value) {
        this.token = token;
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getToken() {
        return token;
    }
}
