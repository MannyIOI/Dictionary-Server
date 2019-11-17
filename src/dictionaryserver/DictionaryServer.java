/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryserver;

import Model.Database;
import Model.Word;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 *
 * @author hp
 */
public class DictionaryServer {

    public static void main(String[] args) throws IOException{
//        String filePath = "E:\\School Files\\4th\\1st Semester\\Distribution System Programming\\Project\\Assignment1\\database.json";
//        System.out.println(args[]);
        Utility.PORT = Integer.parseInt(args[0]);
        Utility.FILE_LOCATION = args[1];
        Server.Start();
    }
}
