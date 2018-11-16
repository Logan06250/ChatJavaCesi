package ChatClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket{

    public static Socket clientSocket;

    public String name;
    public boolean isReady = false;

    public void initClient(String host, String clientName){
        try {
            this.clientSocket = new Socket(host,3000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(clientSocket.isConnected()){
            this.isReady = true;
            this.name = clientName;
        }
    }


    public void startClient(GUIFrame GUI) {

        final Scanner sc = new Scanner(System.in);
        final BufferedReader in;
        final PrintWriter out;

        try {

            out = new PrintWriter(this.clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            out.println(name);
            out.flush();
            out.println("vient de se connecter au chat" );
            out.flush();

            Thread clientSender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while((msg = sc.nextLine()) != null){
                        try {
                            sendMessage(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            clientSender.start();


            Thread receiver = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        while((msg = in.readLine()) != null){
                            if((msg.indexOf("connectionList")) < 0){
                                GUI.setTextArea(msg);
                            }else{
                                String[] connectionList = msg.split(";");
                                String connectionListFormated = "";
                                for(int i=1; i < connectionList.length; i++){
                                    if(!(connectionList[i] == "connectionList")){
                                        connectionListFormated = connectionListFormated + connectionList[i] + "\n";
                                    }
                                }
                                GUI.setTextArea2(connectionListFormated);
                            }
                        }
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {}
                }
            });
            receiver.start();

        } catch (IOException e) {}
    }

    public void sendMessage(String msg) throws IOException {
        final PrintWriter out;
        out = new PrintWriter(clientSocket.getOutputStream());
        out.println(msg);
        out.flush();
    }
}
