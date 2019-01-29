package com.project.app.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.toIntExact;

/**
 * Class that have access to dictionary.json and directly add or read from that file
 * @author      Paweł Krupski
 * @version     1.0
 */
public class DictionaryJSONHandler {



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
            newObject.put("lesson",word.getLesson());
            newObject.put("category",word.getCategory());



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

    /**
     *  Method that returns random word from JSON file.
     * @author Dominika Kunc
     * @return Word randomWord
     */
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
            return new Word((String) json.get("eng"),(String) json.get("pl"),(String)json.get("label"), toIntExact( (long) json.get("lesson")), (String) json.get("category"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  Method that returns specified amount of words on specified level from JSON file.
     * @author Dominika Kunc
     * @param amountOfWords wanted amount of random words
     * @param level wanted level of words
     * @return ArrayList of Words with randomly selected words
     */
    public ArrayList<Word> getWordsFromLevelFromJSON(int amountOfWords, String level){

        /**
         *  return apecified amount of words on specified level from JSON file
         */

        Object obj = null;
        ArrayList <Word> wordsFromLevel = new ArrayList<Word>();
        ArrayList <Word> randomWords = new ArrayList<Word>();


        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("dictionary.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("words");
            for (int i = 0; i < list.size(); ++i) {
                JSONObject word = (JSONObject) list.get(i);
                String levelType = (String) word.get("label");
                if (levelType.equals(level)) {
                    wordsFromLevel.add(new Word((String) word.get("eng"), (String) word.get("pl"), (String) word.get("label"), toIntExact((long) word.get("lesson")), (String) word.get("category")));
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        for(int i = 0; i < amountOfWords; i++)
        {
           int r =  random.nextInt((wordsFromLevel.size()-1 - i) + 1);
           System.out.println("random: "+ r);
           randomWords.add(wordsFromLevel.get(r));
           wordsFromLevel.remove(r);

        }
        return randomWords;
    }

    /**
     *  Method that returns specified amount of words on specified level from JSON file.
     * @author Dominika Kunc
     * @param lessonNumber number of wanted lesson
     * @return topic of specified lesson
     */
    public String getTopicOfLessonFromJSON(int lessonNumber){

        /**
         *  return apecified amount of words on specified level from JSON file
         */

        Object obj = null;
        String topic="";


        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("dictionary.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("words");
            for (int i = 0; i < list.size(); ++i) {
                JSONObject word = (JSONObject) list.get(i);
                int lesson = (Integer) word.get("lesson");
                if (lesson == lessonNumber) {
                    topic = (String) word.get("category");
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return topic;
    }

    /**
     *  Method that returns words from specified lesson from JSON file.
     * @author Dominika Kunc
     * @param lesson waned lesson number
     * @return ArrayList of Words with words from specified lesson
     */
    public ArrayList<Word> getWordsFromLessonFromJSON(int lesson){

        /**
         *  return words from specified lesson from JSON file
         */

        Object obj = null;
        ArrayList <Word> wordsFromLesson = new ArrayList<Word>();

        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("dictionary.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("words");
            for (int i = 0; i < list.size(); ++i) {
                JSONObject word = (JSONObject) list.get(i);
                int lessonNumber = toIntExact((long) word.get("lesson"));
                if (lessonNumber == lesson) {
                    wordsFromLesson.add(new Word((String) word.get("eng"), (String) word.get("pl"), (String) word.get("label"), toIntExact((long) word.get("lesson")), (String) word.get("category")));
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return wordsFromLesson;
    }

    /**
     *  Method that returns amount of unique lessons in JSON file.
     * @author Dominika Kunc
     * @return amount of unique lessons in JSON file
     */
    public int getAmountOfLessonsFromJSON()
    {
        Object obj = null;
        ArrayList <Word> wordsFromLesson = new ArrayList<Word>();
        int amount = 1;

        try {

            //Read whole array of objects
            obj = parser.parse(new FileReader("dictionary.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list = (JSONArray) jsonObject.get("words");
            for (int i = 0; i < list.size(); ++i) {
                JSONObject word = (JSONObject) list.get(i);
                int lessonNumber = toIntExact((long) word.get("lesson"));
                if (lessonNumber > amount) {
                    amount = lessonNumber;
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return amount;
    }

}
