/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hp
 */
public final class User {
    private String _id;
    private String _email;
    private String _password;

    public User(String email, String password){
        setEmail(email);
        setPassword(password);
    }
    public User(String id, String email, String password){
        setId(id);
        setEmail(email);
        setPassword(password);
    }
    
    public String getId() {
        return _id;
    }

    public final void setId(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getPassword() {
        return _password;
    }

    public final void setPassword(String _password) {
        this._password = _password;
    }
    
    @Override
    public String toString(){
        return "User - email : " + getEmail() + " - password : " + getPassword();
    }
    
    
}
