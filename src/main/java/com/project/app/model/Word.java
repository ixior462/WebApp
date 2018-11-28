package com.project.app.model;

public class Word {

    /**
     * @author      Paweł Krupski
     * @info        Class that represents each single word that is included in the dictionary
     * @version     1.0
     */

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

    public String getEng() {
        return eng;
    }

    public String getPl() {
        return pl;
    }

    public String getLabel() {
        return label;
    }

    public int getLesson() {
        return lesson;
    }
    public String getCategory() {
        return category;
    }
}
