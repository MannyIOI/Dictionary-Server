/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryserver;

import Model.Database;
import Model.Word;
import java.util.ArrayList;


/**
 *
 * @author hp
 */
public class DictionaryServer {

    public static void main(String[] args){
        String filePath = "E:\\School Files\\4th\\1st Semester\\Distribution System Programming\\Project\\Assignment1\\database.json";
        
        try{
            Database db = Database.getInstance(filePath);
//            db
            ArrayList<String> list = new ArrayList<>();
            list.add("def21");
            db.addWord(new Word("word2", list));
//            sdb.getWordDefinition("word");
//            System.out.println(db.getWordDefinition("word2"));
//                System.out.println(db.removeDefintion("word2"));
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}
