/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryserver;

import Model.Database;
import Model.Word;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class DictionaryServer {

    public static void main(String[] args){
        try{
            System.out.println(Database.getDatabase().searchWord(new Word("%lo%")));
//            System.out.println(json);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
}
