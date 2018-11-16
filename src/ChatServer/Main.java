package ChatServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerSocketListener server = new ServerSocketListener();
        server.startServer();


    }
}
