package com.project.app.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.StringWriter;
import java.util.Random;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DictionaryJSONHandler {

    /**
     * @author      Paweł Krupski
     * @info        Class that have access to dictionary.json and directly add or read from that file
     * @version     1.0
     */

    private JSONParser parser;

    public DictionaryJSONHandler() {
        this.parser = new JSONParser();
    }


    protected void addWordToJSON(Word word){

        /**
         * Write object Word to a dictionary.json
         *
         * TODO : Check if word doesn't already exist in file nad maybe something else ?
        */

        try {
            System.out.println("Adding to Dictionary:");

            //Open up file to get access to existing array
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("dictionary.json"));

            //get array of words
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonItemInfo = (JSONArray) jsonObject.get("words");

            //create new object from the date that was passed by method's arguments
            JSONObject newObject = new JSONObject();
            newObject.put("eng",word.getEng());
            newObject.put("pl",word.getPl());
            newObject.put("label",word.getLabel());

            //Print object that we will add to the array
            StringWriter out = new StringWriter();
            newObject.writeJSONString(out);
            String jsonText = out.toString();
            System.out.println("\t"+jsonText);

            //Add this new object to array
            jsonItemInfo.add(newObject);
            jsonObject.put("words", jsonItemInfo);

            //Write whole thing to a file
            FileWriter fileToWrite = new FileWriter("dictionary.json", false);
            try {
                fileToWrite.write(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileToWrite.flush();
            fileToWrite.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public Word getRandomWordFromJSON(){

        /**
         *  return random word from JSON file
         */

        Object obj = null;
        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("dictionary.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("words");
            Random rand = new Random();

            //get object from array at random index
            JSONObject json = (JSONObject) parser.parse(list.get( rand.nextInt(list.size())).toString());

            //return object Word
            return new Word((String) json.get("eng"),(String) json.get("pl"),(String)json.get("label"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


}
