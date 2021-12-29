package br.com.puti.core.integrations.http.answer.in;

public interface RequestCallback {
    void call(String author, Object value);
}
