/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author hp
 */
public class Database {

    private static Database db;
    Connection con;
    Statement stmt;
    public Database(String database, String username, String password){
//        Exception
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary","root","");
            this.stmt = con.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public static Database getDatabase(){
        if(db == null) {

            db = new Database("dictionary", "root", "");
            return db;
        }
        return db;
    }
    
    public User addUser(User user) throws SQLException{
        PreparedStatement pstmt=con.prepareStatement("INSERT into `user` (`email`, `password`) values(?,?)");  
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getPassword());
        pstmt.executeUpdate();
        pstmt = con.prepareStatement("SELECT * FROM user where user.email = ?");
        pstmt.setString(1, user.getEmail());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return new User(rs.getString("id"), rs.getString("email"), rs.getString("password"));
    }
    
    public boolean login(User user) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM `user` WHERE user.email = (?) AND user.password = (?)");
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) return true;
        return false;
    }
    
    public Word addWord(Word word) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement("Insert into `word` (`word`) values(?)");
        pstmt.setString(1, word.getWord());
        pstmt.executeUpdate();
        pstmt = con.prepareStatement("SELECT * FROM word where word.word = ?");
        pstmt.setString(1, word.getWord());
        pstmt.setString(1, word.getWord());
        ResultSet rs = pstmt.executeQuery();
        return new Word(rs.getString(0), rs.getString(1));
    }
    
    public void addDefinition(Word word, User user, String definition) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO `definition` (`word_id`, `user_id`, `definition`) values(?, ?, ?)");
        pstmt.setString(1, word.getId());
        pstmt.setString(2, user.getId());
        pstmt.setString(3, definition);
        pstmt.executeUpdate();
    }
    
    public Word getDefinition(String word) throws SQLException, Exception{
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM `word`, `definition`, `user` WHERE definition.word_id = word.id AND definition.user_id = user.id AND word.word = (?)");
        pstmt.setString(1, word);
        ResultSet rs = pstmt.executeQuery();
        Word resultWord = new Word(word, new ArrayList<>());
        while(rs.next()){
            
            resultWord.setWord(word);
            resultWord.addDefinition(rs.getString("definition"));
        }
        if (resultWord.getDefinition().size() < 1) throw new Exception("No Defintions Found for the Error");
        return resultWord;
    }
    
    
}
