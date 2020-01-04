/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author dell
 */
public class SocketConnection extends Thread {

    private Socket socket;
    private static PrintStream printStream;
    boolean serverClosed = false;
    DataInputStream dataInputStream;
    Thread th;

    public SocketConnection() {

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
    public void closeSocketConnection() throws IOException{       
        printStream.close();
        dataInputStream.close();
        socket.close();
    }

    public static PrintStream getPrintStreamInstance() {
        return printStream;
    }

    @Override
    public void run() {
        System.out.println("in run");

        try {

            while (true) {
                String replyMsg = dataInputStream.readLine();
                if (replyMsg != null) {

                    if (replyMsg.equals("closed")) {
                        System.out.println(replyMsg);
                        serverClosed = true;

                    }
                    if (replyMsg.equals("opened")) {
                        serverClosed = false;
                        System.out.println("opened");
                    }

                    if (!replyMsg.equals("opened") && !replyMsg.equals("closed")) {
                        Handler.handle(replyMsg);
                    }
                }

            }
        } catch (IOException e) {
            serverClosed = true;            

        }
    }

}
