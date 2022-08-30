package org.example;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class HelloWorldServer
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                // Print the message
                System.out.println(
                    "Received: [" + new String(reply, ZMQ.CHARSET) + "]"
                );
            }
        }
    }
}