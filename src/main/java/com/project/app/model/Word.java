package com.project.app.model;

/**
 *  Class that represents each single word that is included in the dictionary
 * @author      Paweł Krupski
 * @version     1.0
 */
public class Word {



    private String eng;
    private String pl;
    private String label;
    private int lesson;
    private String category;



    public Word( String eng, String pl, String label, int lesson, String category){
        this.eng = eng;
        this.pl = pl;
        this.label = label;
        this.lesson = lesson;
        this.category = category;

    }


    /**
     * Method that returns Word's english version
     * @return Word's english version
     */
    public String getEng() {
        return eng;
    }

    /**
     * Method that returns Word's polish version
     * @return Word's polish version
     */
    public String getPl() {
        return pl;
    }

    /**
     * Method that returns Word's level
     * @return Word's level
     */
    public String getLabel() {
        return label;
    }

    /**
     * Method that returns lesson of Word
     * @return lesson of Word
     */
    public int getLesson() {
        return lesson;
    }

    /**
     * Method that returns Word's category
     * @return Word's category
     */
    public String getCategory() {
        return category;
    }
}
