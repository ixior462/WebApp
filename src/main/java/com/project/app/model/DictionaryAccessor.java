package com.project.app.model;

import java.util.ArrayList;

public class DictionaryAccessor {

    /**
     * @author      Paweł Krupski
     * @info        Class that allows to get access to dictionary database (add word, get random word)
     * @version     1.0
     */

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

    public int getAmountOfLessons(){
        return handler.getAmountOfLessonsFromJSON();
    }

    public ArrayList<Word> getWordsFromLesson(int lesson){
        return handler.getWordsFromLessonFromJSON(lesson);
    }
}
