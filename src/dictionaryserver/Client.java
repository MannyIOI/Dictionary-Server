/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryserver;


import java.io.*;
import java.net.*;
import java.util.Scanner;
// Client class
public class Client {
    public static void Start(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");
            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            // the following loop performs the exchange of
            // information between client and client handler
        //        while (true) {
            System.out.println(dis.readUTF());
        //            String tosend = "{'query': 'search', 'searchKey': 'dhabtegabriel@gmail.com', 'password':'123456789'}";
//            String tosend = "{'query': 'getDefinition', 'word': 'word'}";
                String tosend = "{'query': 'addWord', 'word': 'newWord', 'def': ['def1', 'def2', 'def3']}";
            
//                String tosend = "{'query': 'removeWord', 'word': 'wordToBeRemoved'}";

            dos.writeUTF(tosend);
                // printing date or time as requested by client
            String received = dis.readUTF();
            System.out.println(received);

            tosend = "Exit";
            dos.writeUTF(tosend);
            // If client sends exit,close this connection
            // and then break from the while loop
            if (tosend.equals("Exit")) {
                System.out.println("Closing this connection : " + s);
                s.close();
                System.out.println("Connection closed");
            }
            scn.close();
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}