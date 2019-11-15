/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryserver;

import Model.Database;
import Model.User;
import Model.Word;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.net.*;
// Server class
public class Server {
 public static void main(String[] args) throws IOException {
 // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);
        // running infinite loop for getting
        // client request

        while (true) {
            Socket s = null;
            try {
            // socket object to receive incoming client requests
                System.out.println("Listening for a new client ... ");
                s = ss.accept();
                System.out.println("A new client is connected : " + s);
                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                System.out.println("Assigning new thread for this client");
                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);
                // Invoking the start() method
                t.start();
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
 }
}
// ClientHandler class
class ClientHandler extends Thread {
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    String fileLocation = "E:\\School Files\\4th\\1st Semester\\Distribution System Programming\\Project\\Assignment1\\database.json";
    Database database = Database.getInstance(fileLocation);
    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }
    @Override
    public void run() {
        String received;
        String toreturn;
        while (true) {
            try {
            // Ask user what he wants
                dos.writeUTF("What do you want?");
                // receive the answer from client
                received = dis.readUTF();
                if (received.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                System.out.println("received " + received);
                JSONObject jsonRequest = new JSONObject(received);

                JSONObject jsonResult = new JSONObject();
                switch (jsonRequest.getString("query")) {
                    case "getDefinition":
                        jsonResult.put("res", database.getWordDefinition(jsonRequest.getString("word").toLowerCase()));
                        jsonResult.put("mesage", "message of the operation being executed successfully");
                        break;
                        
                    case "addWord":
                        jsonResult.put("res", "return the added word");
                        jsonResult.put("message", "message of the operation being executed successfully");
                        dos.writeUTF(jsonResult.toString());
                        break;
                        
                    case "getAllWords":
                        jsonResult.put("res", "return all the list of the words in the database");
                        jsonResult.put("message", "This is the list of all the words");
                        dos.writeUTF(jsonResult.toString());
                        break;
                        
                    case "removeWord":
                        jsonResult.put("res", "return the added definition to the word");
                        jsonResult.put("message", "");
                        break;
                    default:
                        jsonResult.put("res", "ERROR INPUT");
                        jsonResult.put("message", "Invalid input is provided");
                        dos.writeUTF(jsonResult.toString());
                        break;
                }
                
                dos.writeUTF(jsonResult.toString());
            } catch (IOException| JSONException e) {
                e.printStackTrace();

            }
        }
        try {
        // closing resources
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}