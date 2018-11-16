package ChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ClientsPool {

    public static Vector<Socket> listClient = new Vector();
    public static Vector<String> listClientName = new Vector();
    public static LogWriter logWriter;

    public static void sendForEveryClient(String msg) {
        try {
            for (Socket aListClient : listClient) {
                final PrintWriter out;
                out = new PrintWriter(aListClient.getOutputStream());
                out.println(msg);
                out.flush();
                logWriter.writeLog(msg);
            }
        } catch (Exception e) {}
    }


    public static void refreshConnectionList(){
        String connectionList = "connectionList;";

        try {
            for (String aListClientName : listClientName) {
                connectionList = connectionList + aListClientName + ";";
            }
        } catch (Exception e) {}
        sendForEveryClient(connectionList);
    }


    public static void runPool(ServerSocket server) throws IOException {
        final Scanner sc = new Scanner(System.in);
        logWriter = new LogWriter();
        try {
            Thread envoyer = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while (true) {
                        msg = sc.nextLine();
                        sendForEveryClient(msg);
                    }
                }
            });
            envoyer.start();
        } catch (Exception e) {}
        try {
            while (true) {
                Socket client = server.accept();
                System.out.println("Nouveau client connecté à l'IP : " + client.getInetAddress());
                logWriter.writeLog("Nouveau client connecté à l'IP : " + client.getInetAddress());
                Thread t = new Thread(() -> {
                    try {
                        final BufferedReader inClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        final String name = inClient.readLine();
                        listClient.add(client);
                        listClientName.add(name);
                        refreshConnectionList();
                        Thread receiver = new Thread(new Runnable() {
                            String msg;
                            @Override
                            public synchronized void run(){
                                try {
                                    while ((msg = inClient.readLine()) != null) {
                                        sendForEveryClient(name + " : " + msg);
                                        System.out.println(name + " : " + msg);
                                    }
                                } catch (IOException e) {
                                    String tmpMsg = name + " vient de se déconnecter";
                                    System.out.println(tmpMsg);
                                    sendForEveryClient(tmpMsg);
                                    listClientName.remove(name);
                                    listClient.remove(client);
                                    refreshConnectionList();
                                    try {
                                        inClient.close();

                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        });
                        receiver.start();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                t.start();
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }
}
