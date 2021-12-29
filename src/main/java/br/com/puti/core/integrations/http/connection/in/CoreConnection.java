package br.com.puti.core.integrations.http.connection.in;

import br.com.puti.core.integrations.http.answer.in.Request;
import br.com.puti.core.integrations.http.answer.in.RequestCallback;

public interface CoreConnection {

    void setRequestProperty(String arg,Object arg1);

    Request getInput();

    boolean isAccept();

    boolean isAnswer();

    void setRequest(RequestCallback callback);

    Object getOption(String arg0);

}
