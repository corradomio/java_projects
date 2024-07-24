import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private static void checkPort(String host, int port) {
        try {
            Socket client = new Socket();
            client.connect(new InetSocketAddress(host, port), 500);
            // Socket client = new Socket(host, port);
            client.getOutputStream().write('.');
            client.close();
            System.out.printf("... %d available\n", port);
        } catch (IOException e) {
            // System.out.printf("... port %d NOT available\n", port);
        }
    }

    public static void main(String[] args) {
        String host = "localhost";
        int startPort=8080, endPort=8080;
        System.out.println("Hello world!");
        switch (args.length) {
            case 0:
                host = "localhost";
                break;
            case 1:
                host = args[0];
                break;
            case 2:
                host = args[0];
                startPort = Integer.parseInt(args[1]);
                endPort = startPort;
                break;
            case 3:
                host = args[0];
                startPort = Integer.parseInt(args[1]);
                endPort = Integer.parseInt(args[2]);
        }

        for (int port=startPort; port <= endPort; ++port) {
            // System.out.printf("Check port %s:%d\n", host, port);
            checkPort(host, port);
        }

    }
}