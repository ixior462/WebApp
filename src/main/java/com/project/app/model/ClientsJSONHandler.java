package com.project.app.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

public class ClientsJSONHandler {
    //Class that will acces Clients JSON file
    private JSONParser parser;

    public ClientsJSONHandler() {
        this.parser = new JSONParser();
    }


    public void addClientToJSON(Client client) {

        /**
         * Write object Word to a dictionary.json
         *
         * TODO : Check if word doesn't already exist in file nad maybe something else ?
         */

        try {
            System.out.println("Adding to Clients Data Base:");

            //Open up file to get access to existing array
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("clients.json"));

            //get array of words
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonItemInfo = (JSONArray) jsonObject.get("clients");

            //create new object from the date that was passed by method's arguments
            JSONObject newObject = new JSONObject();
            newObject.put("login", client.getLogin());
            newObject.put("password", client.getPassword());


            //Print object that we will add to the array
            StringWriter out = new StringWriter();
            newObject.writeJSONString(out);
            String jsonText = out.toString();
            System.out.println("\t" + jsonText);

            //Add this new object to array
            jsonItemInfo.add(newObject);
            jsonObject.put("clients", jsonItemInfo);

            //Write whole thing to a file
            FileWriter fileToWrite = new FileWriter("clients.json", false);
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



    public Client getClientFromJSON(String login){

        /**
         *  return client from JSON file
         */

        Object obj = null;
        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("clients.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("clients");
            for (int i = 0; i < list.size(); ++i) {
                JSONObject client = (JSONObject) list.get(i);
                String loginFromDataBase = (String) client.get("login");
                if(loginFromDataBase.equals(login))
                {
                    return new Client((String) client.get("login"),(String) client.get("password"));
                }
                // ...
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}