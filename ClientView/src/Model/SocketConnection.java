/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import clientview.NotificationGUI;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author dell
 */
public class SocketConnection extends Thread {

    private static SocketConnection instance = null;
    private Socket socket;
    private PrintStream printStream;
    private boolean serverClosed = false;
    DataInputStream dataInputStream;
    Thread th;

    private SocketConnection() {

        try {

            socket = new Socket("127.0.0.1", 5005);

            dataInputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        th = new Thread(this);
        th.start();

    }

    public boolean isServerClosed() {
        return serverClosed;
    }

    public static SocketConnection getInstance() {
        if (instance == null) {
            synchronized (SocketConnection.class) {
                if (instance == null) {
                    instance = new SocketConnection();
                }
            }
        }
        return instance;
    }

    public void closeSocketConnection() throws IOException {
        printStream.println("clientClosed");
        printStream.close();
        dataInputStream.close();
        socket.close();
    }

    public PrintStream getPrintStreamInstance() {
        return printStream;
    }

    @Override
    public void run() {
        System.out.println("in run");

        try {

            while (true) {
                String replyMsg = dataInputStream.readLine();
                if (replyMsg != null) {

                    if (replyMsg.equals("notification received")) {
                        NotificationGUI.receiveNotificationTray();
                    } else if (replyMsg.equals("closed")) {
                        System.out.println(replyMsg);
                        serverClosed = true;

                    } else if (replyMsg.equals("opened")) {
                        serverClosed = false;
                        System.out.println("opened");
                    } else if (!replyMsg.equals("opened") && !replyMsg.equals("closed") && !replyMsg.equals("notification received")) {
                        Handler.handle(replyMsg);
                    }
                }

            }
        } catch (IOException e) {
            serverClosed = true;

        }
    }

}
