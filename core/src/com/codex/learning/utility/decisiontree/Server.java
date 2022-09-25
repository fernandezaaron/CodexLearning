package com.codex.learning.utility.decisiontree;
import java.io.*;
import java.net.*;

public class Server {
    private String messageFromClient;
    private ServerSocket server;
    private Socket connected;
    private DataOutputStream output;
    private DataInputStream input;
    private BufferedReader inputFromUser;


    public Server() throws Exception{
        server = new ServerSocket (1800);
        System.out.println("Waiting for client on port 1800");

        Runtime.getRuntime().exec("cmd /c start " + System.getProperty("user.dir") + "\\assets\\model\\main.exe");

        connected = server.accept();


        System.out.println("The client is connected...");
        output = new DataOutputStream(connected.getOutputStream());
        input = new DataInputStream(connected.getInputStream());
        inputFromUser = new BufferedReader(new InputStreamReader(System.in));
    }

    public String calculateMLResult(String dataset) throws IOException {
        output.writeUTF(dataset);
        output.flush();
        messageFromClient = input.readUTF();
        return messageFromClient;
    }
}
