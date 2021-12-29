package br.com.puti.core.integrations.sockets;

import br.com.puti.core.integrations.sockets.client.CoreClient;
import br.com.puti.core.integrations.sockets.server.CoreServer;
import br.com.puti.core.loader.CoreLoader;

public class SocketModule {

    private CoreLoader loader;
    private CoreServer server = null;
    private CoreClient client = null;

    public SocketModule(CoreLoader loader) {
        this.loader = loader;
        this.init();
    }

    private void init() {
        try {
            if (this.server == null) server = new CoreServer();
            else if (this.client == null) client = new CoreClient();

            loader.send("SocketModule iniciado.");
        } catch (Exception ex) {
            loader.send("Falha ao iniciar o modulo socket, Mensage: " + ex.getMessage() + " -- " + ex.getLocalizedMessage() + " -- " + ex.getCause());
        }
    }

    public boolean isServer() {
        return server != null;
    }

    public boolean isClient() {
        return client != null;
    }

    public CoreClient getClient() {
        return client;
    }

    public CoreServer getServer() {
        return server;
    }
}
