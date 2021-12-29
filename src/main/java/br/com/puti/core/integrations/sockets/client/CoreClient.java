package br.com.puti.core.integrations.sockets.client;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.Serializable;

public class CoreClient implements Serializable {

    private ZMQ.Socket socket;

    public CoreClient() {
        try (ZContext context = new ZContext()) {
            System.out.println("Connecting to hello world server");

            //  Socket to talk to server
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");

            for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
                String request = "Hello";
                System.out.println("Sending Hello " + requestNbr);
                socket.send(request.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                System.out.println(
                        "Received " + new String(reply, ZMQ.CHARSET) + " " +
                                requestNbr
                );
            }
        }
    }

    public ZMQ.Socket getSocket() {
        return socket;
    }

    public static void main(String[] args) {
        CoreClient coreClient = new CoreClient();
//        coreClient.getSocket().send("Hello!");
    }
}
