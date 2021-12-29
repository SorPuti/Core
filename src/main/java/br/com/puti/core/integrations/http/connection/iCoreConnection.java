package br.com.puti.core.integrations.http.connection;

import br.com.puti.core.integrations.http.answer.iRequest;
import br.com.puti.core.integrations.http.answer.in.Request;
import br.com.puti.core.integrations.http.answer.in.RequestCallback;
import br.com.puti.core.integrations.http.body.in.Body;
import br.com.puti.core.integrations.http.connection.in.CoreConnection;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

public class iCoreConnection implements CoreConnection {

    protected Body body;
    protected TreeMap<String, Object> options = new TreeMap<>();
    protected RequestCallback callback;

    public iCoreConnection(Body body) {
        this.body = body;
    }

    @Override
    public void setRequestProperty(String arg, Object arg1) {
        if (options.containsKey(arg))
            options.remove(arg);
        else
            options.put(arg, arg1);
    }

    @Override
    public Request getInput() {
        AtomicReference<Request> request = null;

        synchronized (this) {
            if (callback == null)
                callback = (author, value) -> {
                    assert false;
                    request.set(new iRequest(author, value));
                };
        }

        assert false;
        return request.get();
    }

    @Override
    public boolean isAccept() {
        if (options.containsKey("accept"))
            try {
                return Boolean.parseBoolean(options.get("accept").toString());
            } catch (Exception ex) {
                return false;
            }
        else
            return false;
    }

    @Override
    public boolean isAnswer() {
        if (options.containsKey("request"))
            try {
                return Boolean.parseBoolean(options.get("request").toString());
            } catch (Exception ex) {
                return false;
            }
        else
            return false;
    }

    @Override
    public void setRequest(RequestCallback callback) {
        this.callback = callback;
    }

    @Override
    public Object getOption(String arg0) {
        return options.getOrDefault(arg0, null);
    }
}
