package br.com.puti.core.integrations.sockets.server.objects;

import br.com.puti.core.integrations.sockets.client.CoreClient;

import java.io.Serializable;

public class ClientModel implements Serializable {

    protected String name;
    protected CoreClient connection;

    public ClientModel(String name, CoreClient connection) {
        this.name = name;
        this.connection = connection;
    }

    public CoreClient getConnection() {
        return connection;
    }

    public String getName() {
        return name;
    }

}