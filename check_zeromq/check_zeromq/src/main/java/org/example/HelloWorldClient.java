package org.example;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");

            for (int i=0; i<10; ++i) {
                String message = "Hello, world!";
                System.out.println(message);
                socket.send(message.getBytes(ZMQ.CHARSET), 0);

                byte[] response = socket.recv();
                System.out.println(new String(response));
            }
        }
    }
}
