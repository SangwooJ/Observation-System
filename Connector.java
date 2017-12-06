import java.net.ServerSocket;
import java.net.Socket;

public class Connector extends Thread {
    private static final int ServerPort = 9999;
    private Server_Controller server_controller;
    private Socket client;

    public Connector(Server_Controller server_controller) {
        this.server_controller = server_controller;
    }

    @Override
    public void run() {
        try {
            System.out.println("S : Connecting...");
            ServerSocket serverSocket = new ServerSocket(ServerPort);

            while (true) {
                client = serverSocket.accept();
                System.out.println("S : Receiving...");
                setConnection();
            }
        } catch (Exception e) {
            System.out.println("S : Error");
            e.printStackTrace();
        }
    }

    public void setConnection() {
        // TODO implement here
        server_controller.addNode(client);

    }

}