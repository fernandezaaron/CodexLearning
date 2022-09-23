package com.codex.learning.utility.decisiontree;

import java.io.*;
import java.net.*;

public class Server2 {
    public static void main(String argv[]) throws Exception {
        String messageFromClient;
        String messageToClient;

        ServerSocket Server = new ServerSocket (1800);

        System.out.println("Waiting for client on port 1800");


            Socket connected = Server.accept();
            System.out.println("The client is connected...");

            DataOutputStream output = new DataOutputStream(connected.getOutputStream());
            DataInputStream input = new DataInputStream(connected.getInputStream());

            BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.print("SEND(Type Q or q to Quit): ");

                messageToClient = inputFromUser.readLine();
                if(messageToClient.equals("q") || messageToClient.equals("Q")){
                    connected.close();
                    output.close();
                    break;
                }
                else{
                    output.writeUTF(messageToClient);
                    output.flush();
                }
                messageFromClient = input.readUTF();

                if(messageFromClient.equals("q") || messageFromClient.equals("Q")) {
                    connected.close();
                    input.close();
                    break;
                }
                else{
                    System.out.println("RECIEVED: " + messageFromClient);
                }
            }

    }
}
