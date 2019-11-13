/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryserver;

import Model.Database;
import Model.User;
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

    Database database = Database.getDatabase();
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
                JSONObject jsonObject = new JSONObject(received);

                switch (jsonObject.getString("query")) {
                    case "login":
                        boolean isValid = Database.getDatabase().login(new User(jsonObject.getString("email"),jsonObject.getString("password")));
                        if(isValid){
                            System.out.println("You've logged in");
                        }
                        else {
                            System.out.println("Wrong username and password");
                        }
                        break;
                    case "Search":
                        System.out.println("You're in search");
                        break;
                    case "AddWord":
                        System.out.println("You're in add word");
                        break;
                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException| JSONException | SQLException e) {
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