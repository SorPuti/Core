package br.com.puti.core.integrations.sockets.server;

import br.com.puti.core.integrations.sockets.server.objects.ClientModel;
import br.com.puti.core.integrations.sockets.server.threads.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CoreServer implements Runnable {

    private ServerSocket serverSocket = null;
    private Thread thread = null;
    private final AtomicReference<ServerThread> client = new AtomicReference<>();
    private final List<ClientModel> connections = new ArrayList<>();

    public CoreServer() {
        try {
            int port = 8081;
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado na porta " + serverSocket.getLocalPort() + "...");
            System.out.println("Esperando pelo cliente...");
            thread = new Thread(this);
            thread.start();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                addThreadClient(serverSocket.accept());
            } catch (IOException e) {
                System.out.println("Error : " + e);
            }
        }
    }

    public void addThreadClient(Socket socket) {
        client.set(new ServerThread());
        client.get().start();
    }

    public void addConnection(ClientModel connection) {
        connections.add(connection);
    }

    public int getConnections() {
        return connections.size();
    }

    public static void main(String[] args) {
        CoreServer server = new CoreServer();
    }
}