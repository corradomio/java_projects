import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {

    static class TestPort implements Runnable {

        private int port;
        private ServerSocket socket;

        public TestPort(int port) {
            this.port = port;
            try {
                this.socket = new ServerSocket(this.port);
            } catch (IOException e) {

            }
        }

        @Override
        public void run() {
            System.out.printf("Listen on port %d\n", this.port);
            while (!Thread.interrupted()) {
                int b;
                try {
                    Socket socket = this.socket.accept();
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    while ((b = is.read()) != -1 && !Thread.currentThread().isInterrupted()) {
                        os.write(b);
                        if (b == '.')
                            Thread.currentThread().interrupt();
                    }
                    is.close();
                    os.close();
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        int startPort=8080, endPort=8080;
        System.out.println("Hello world!");
        switch (args.length) {
            case 0:
                break;
            case 1:
                startPort = Integer.parseInt(args[0]);
                endPort = startPort;
                break;
            case 2:
                startPort = Integer.parseInt(args[0]);
                endPort = Integer.parseInt(args[1]);
        }

        List<Thread> testPorts = new ArrayList<>();
        for (int port=startPort; port<=endPort; port++) {
            TestPort tp = new TestPort(port);
            Thread t = new Thread(tp);
            t.start();
            testPorts.add(t);
        }

        for (Thread t : testPorts) {
            try {
                if (t.isAlive())
                    t.join(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}