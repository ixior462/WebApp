package com.project.app.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.toIntExact;

/**
 *  Class that have access to clients.json and directly add or read from that file
 * @author      Paweł Krupski
 * @version     1.0
 */
public class ClientsJSONHandler {
    



    //Class that will access Clients JSON file
    private JSONParser parser;

    public ClientsJSONHandler() {
        this.parser = new JSONParser();
    }

    /**
     *  Method that adds new Client to JSON file.
     * @author Paweł Krupski
     * @param client new Client object to be added
     *
     */
    public void addClientToJSON(Client client) {

        /**
         * Write object Word to a dictionary.json
         *
         *
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
            newObject.put("email", client.getEmail());
            newObject.put("elo", client.getElo());
            newObject.put("level", client.getLevel());
            newObject.put("lastLesson", client.getLastLesson());
            newObject.put("stage", client.getStage());



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


    /**
     *  Method that returns Client with specified login form JSON file.
     * @author Paweł Krupski
     * @param login login of wanted Client
     * @return Client client
     */
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
                    return new Client(
                            (String) client.get("login"),
                            (String) client.get("password"),
                            (String) client.get("email"),
                            (String) client.get("level"),
                            toIntExact( (long) client.get("elo")),
                            toIntExact( (long) client.get("lastLesson")),
                            toIntExact( (long) client.get("stage")));
                }
                // ...
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  Method that returns ArrayList of Clients containing all Clients from JSON file and sorted in descending order.
     * @author Dominika Kunc
     * @return ArrayList of Clients ranking
     */
    public ArrayList<Client> getRankingFromJSON()
    {
        /**
         *  return client from JSON file
         */

        ArrayList<Client> ranking = new ArrayList<Client>();

        Object obj = null;
        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("clients.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("clients");
            for (int i = 0; i < list.size(); ++i) {
                JSONObject client = (JSONObject) list.get(i);

                    ranking.add( new Client(
                            (String) client.get("login"),
                            (String) client.get("password"),
                            (String) client.get("email"),
                            (String) client.get("level"),
                            toIntExact( (long) client.get("elo")),
                            toIntExact( (long) client.get("lastLesson")),
                            toIntExact( (long) client.get("stage"))));

                // ...
            }

            Collections.sort(ranking, new Comparator<Client>(){
                public int compare(Client c1, Client c2){
                    if(c1.getElo()== c2.getElo())
                        return 0;
                    return c1.getElo() < c2.getElo() ? 1 : -1;
                }
            });

            for(int i = 0; i < ranking.size(); i++)
            {
                Client c = ranking.get(i);
                System.out.println((i+1)+". "+c.getLogin()+" - "+c.getElo());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return ranking;
    }


    /**
     *  Method that updates ELO points of specified Client in JSON File.
     * @author Dominika Kunc
     * @param login login of Client to be updated
     * @param newElo new ELO points value
     * @return Client updatedClient
     */
    public Client updateEloInJSON(String login, int newElo){

        /**
         *  return client from JSON file
         */

        Object obj = null;
        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("clients.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("clients");
            JSONObject updatedClient = null;
            for (int i = 0; i < list.size(); ++i) {
                JSONObject client = (JSONObject) list.get(i);
                String loginFromDataBase = (String) client.get("login");
                if(loginFromDataBase.equals(login))
                {
                    client.remove("elo");
                    client.put("elo", newElo);
                    updatedClient = client;
                }
                // ...
            }

            //Print object that we will add to the array
            StringWriter out = new StringWriter();
            updatedClient.writeJSONString(out);
            String jsonText = out.toString();
            System.out.println("\t" + jsonText);

            //Add this new object to array
            jsonObject.put("clients", list);

            //Write whole thing to a file
            FileWriter fileToWrite = new FileWriter("clients.json", false);
            try {
                fileToWrite.write(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileToWrite.flush();
            fileToWrite.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  Method that checks if specified username exists in JSON file.
     * @author Paweł Krupski
     * @param name username to be checked
     * @return true if exist false if not
     */
    public boolean usernameAlreadyExist(String name) {

        /**
         *  check if client with that name already exist
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
                if(loginFromDataBase.equals(name))
                {
                   return true;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return false;


    }

    /**
     *  Method that updates Lesson of specified Client in JSON File.
     * @author Dominika Kunc
     * @param login login of Cient to be updated
     * @param newLesson new lesson number
     * @return Client updatedClient
     */
    public Client updateLessonInJSON(String login, int newLesson){

        /**
         *  return client from JSON file
         */

        Object obj = null;
        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("clients.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("clients");
            JSONObject updatedClient = null;
            for (int i = 0; i < list.size(); ++i) {
                JSONObject client = (JSONObject) list.get(i);
                String loginFromDataBase = (String) client.get("login");
                if(loginFromDataBase.equals(login))
                {
                    client.remove("lesson");
                    client.put("lesson", newLesson);
                    updatedClient = client;
                }
                // ...
            }

            //Print object that we will add to the array
            StringWriter out = new StringWriter();
            updatedClient.writeJSONString(out);
            String jsonText = out.toString();
            System.out.println("\t" + jsonText);

            //Add this new object to array
            jsonObject.put("clients", list);

            //Write whole thing to a file
            FileWriter fileToWrite = new FileWriter("clients.json", false);
            try {
                fileToWrite.write(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileToWrite.flush();
            fileToWrite.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  Method that updates stage of specified Client in JSON File.
     * @author Dominika Kunc
     * @param login login of Client to be updated
     * @param newStage new stage value
     * @return Client updatedClient
     */
    public Client updateStageInJSON(String login, int newStage){

        /**
         *  return client from JSON file
         */

        Object obj = null;
        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("clients.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("clients");
            JSONObject updatedClient = null;
            for (int i = 0; i < list.size(); ++i) {
                JSONObject client = (JSONObject) list.get(i);
                String loginFromDataBase = (String) client.get("login");
                if(loginFromDataBase.equals(login))
                {
                    client.remove("stage");
                    client.put("stage", newStage);
                    updatedClient = client;
                }
                // ...
            }

            //Print object that we will add to the array
            StringWriter out = new StringWriter();
            updatedClient.writeJSONString(out);
            String jsonText = out.toString();
            System.out.println("\t" + jsonText);

            //Add this new object to array
            jsonObject.put("clients", list);

            //Write whole thing to a file
            FileWriter fileToWrite = new FileWriter("clients.json", false);
            try {
                fileToWrite.write(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileToWrite.flush();
            fileToWrite.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}