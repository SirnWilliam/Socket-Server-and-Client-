/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sirn William
 */
import java.util.Scanner;

public class Socket_Test {
    // Own IP: 127.0.0.1
    public static void main(String[] args) {
        System.out.println("Enter port for this server to listen on: ");
        int iPort = new Scanner(System.in).nextInt();

        SocketServer server = new SocketServer(iPort);
        Thread oThread = new Thread(server);
        oThread.start();

        System.out.println("Enter the other server's IP address:");
        String sOtherServerIP = new Scanner(System.in).nextLine();

        System.out.println("Enter the other server's port: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();

        while (true) {
            System.out.println("Message to send: ");
            String sMessage = new Scanner(System.in).nextLine();
             
            SocketClient oClient = new SocketClient();
            String sReply = oClient.connectForOneMessage(sOtherServerIP, iOtherServerPort, sMessage);

            System.out.printf("Reply from server: %s\n", sReply);
        }
    }
}

