package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketListener {

    public static void startServer() throws IOException{

        int port = 3000;

        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        ClientsPool clientspool = new ClientsPool();
        clientspool.runPool(server);

    }
}
