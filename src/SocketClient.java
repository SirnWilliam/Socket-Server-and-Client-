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
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    public String connectForOneMessage(String sIP, int iPort, String sMessage) {
        try (Socket oSocket = new Socket()) {
            oSocket.connect(new InetSocketAddress(sIP, iPort), 5000);

            OutputStream output = oSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(sMessage);
            writer.flush();

            InputStream input = oSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String sReceivedMessage = reader.readLine();

            oSocket.close();

            return sReceivedMessage;
        }
        catch (Exception ex) {
            System.err.printf("Error from client: %s\n", ex.getMessage());
            return null;
        }
    }
}
