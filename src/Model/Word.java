/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class Word {
    private String _id;

    
    private String _word;
    private List<String> _definition;

    public Word(String word){
        setWord(word);
    }
    
    public Word(String id, String word){
        setId(id);
        setWord(word);
    }
    
    public Word(String word, List<String> definition){
        setWord(word);
        setDefinition(definition);
    }
    
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
    public String getWord() {
        return _word;
    }

    public final void setWord(String _word) {
        this._word = _word;
    }

    public List<String> getDefinition() {
        return _definition;
    }

    public void setDefinition(List<String> _definition) {
        this._definition = _definition;
    }
    
    public void addDefinition(String definition){
        this._definition.add(definition);
    }

    public String getString(){
        JSONObject jsonResult = new JSONObject();
        try{
            jsonResult.put("id", getId());
            jsonResult.put("word", getWord());
            jsonResult.put("definition", getDefinition());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return jsonResult.toString();
    }
    
    @Override
    public String toString(){
        return getString();
    }
    
    
}
