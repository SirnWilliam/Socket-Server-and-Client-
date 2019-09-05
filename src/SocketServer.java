/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sirn William
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private int thisServerPort;

    public SocketServer(int iPort) {
        thisServerPort = iPort;
    }

    @Override
    public void run() {
        try (ServerSocket oServerSocket = new ServerSocket(thisServerPort)){
            System.out.printf("Server is listening on Port %d.\n", thisServerPort);

            while (true) {
                // Server listens for connection
                int result = 0;
                Socket oSocket = oServerSocket.accept();

                System.out.printf("[server]: New client connected: %s\n", oSocket.getRemoteSocketAddress());//to get the address who is connecting to my computer
                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String sReceivedMessage = reader.readLine();
                System.out.printf("[server]: Message received: %s\n", sReceivedMessage);
                String[] messageWithoutComma = sReceivedMessage.split(",");
                ////////getting the result.
                for(int i=0;i<messageWithoutComma.length;i++){           //OR int var_1 = messageWithoutComma[0];
                    result += Integer.parseInt(messageWithoutComma[i]);   //   int var_2 = messageWithoutComma[1];
                }                                                        //   int var_3 = messageWithoutComma[2];
                System.out.println("The result is: " + result);           //   int result = var_1 + var_2 + var_3;
                
                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                writer.println("Message received.");
                writer.flush();

                oSocket.close();
            }
        }
        catch (Exception ex) {
            System.err.printf("Error from server: %s\n", ex.getMessage());
        }
    }
}

