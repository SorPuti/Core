package br.com.puti.core.component.json.jackson;

public class RequestBodyParseException extends RuntimeException {
  public RequestBodyParseException(String message) {
    super(message);
  }
}
