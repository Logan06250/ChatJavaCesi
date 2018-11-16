package ChatClient;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        ClientSocket clientSocket = new ClientSocket();
        GUIFrame GUI = new GUIFrame(clientSocket);
        GUIFrameConnection GUIConnection = new GUIFrameConnection(clientSocket);
        GUIConnection.setVisible(true);
        while (clientSocket.isReady == false){
            Thread.sleep(2000);
            System.out.println("En attente de connection");
        }
        GUIConnection.setVisible(false);
        GUI.setVisible(true);
        clientSocket.startClient(GUI);
    }

}
