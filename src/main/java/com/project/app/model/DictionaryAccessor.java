package com.project.app.model;

import java.util.ArrayList;
/**
 *  Class that allows to get access to dictionary database (add word, get random word)
 * @author      Paweł Krupski
 * @version     1.0
 */
public class DictionaryAccessor {



    private DictionaryJSONHandler handler;


    public DictionaryAccessor() {
            this.handler = new DictionaryJSONHandler();
    }

    public void addArrayListOfWordsToJSON(ArrayList<Word> words){
        for (Word word : words) {
            handler.addWordToJSON(word);
        }
    }

    public Word getRandomWord(){
        return handler.getRandomWordFromJSON();
    }

    /**
     *  Method that returns amount of unique lessons in JSON file.
     * @author Dominika Kunc
     * @return amount of unique lessons in JSON file
     */
    public int getAmountOfLessons(){
        return handler.getAmountOfLessonsFromJSON();
    }

    /**
     *  Method that returns amount of unique lessons on specified level in JSON file.
     * @author Dominika Kunc
     * @return amount of unique lessons on specified levelin JSON file
     */
    public int getAmountOfLessonsOnLevel(String level){
        return handler.getAmountOfLessonsOnLevelFromJSON(level);
    }

    /**
     *  Method that returns level of specified lesson in JSON file.
     * @author Dominika Kunc
     * @param lessonNumber number of lesson to find
     * @return level of specified lesson
     */
    public String getLevelOfLesson(int lessonNumber){
        return handler.getLevelOfLessonFromJSON(lessonNumber);
    }

    /**
     *  Method that returns topic of specified lesson in JSON file.
     * @author Dominika Kunc
     * @param lessonNumber number of lesson to find
     * @return topic of specified lesson
     */
    public String getTopicOfLesson(int lessonNumber){
        return handler.getTopicOfLessonFromJSON(lessonNumber);
    }

    /**
     *  Method that returns words from specified lesson from JSON file.
     * @author Dominika Kunc
     * @param lesson wanted lesson number
     * @return ArrayList of Words with words from specified lesson
     */
    public ArrayList<Word> getWordsFromLesson(int lesson){
        return handler.getWordsFromLessonFromJSON(lesson);
    }

    /**
     *  Method that returns specified amount of words on specified level from JSON file.
     * @author Dominika Kunc
     * @param amountOfWords wanted amount of random words
     * @param level wanted level of words
     * @return ArrayList of Words with randomly selected words
     */
    public ArrayList<Word> getWordsFromLevel(String level, int amountOfWords){ return handler.getWordsFromLevelFromJSON(amountOfWords, level); }

}
