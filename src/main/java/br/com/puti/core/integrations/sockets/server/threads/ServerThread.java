package br.com.puti.core.integrations.sockets.server.threads;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {

    private List<ZMQ.Socket> connections = new ArrayList<>();

    public ServerThread() {
        this.setup();
    }

    public void setup() {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");
            System.out.println("Listeners...");

            while (!Thread.currentThread().isInterrupted()) {
                byte[] reply = socket.recv(0);

                System.out.println(
                        "Recebido: [" + new String(reply, ZMQ.CHARSET) + "]"
                );
                String response = "Hello, world!";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }

    public List<ZMQ.Socket> getConnections() {
        return connections;
    }

    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread();
    }
}