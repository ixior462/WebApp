package com.project.app.model;

/**
 *          Class that represents each single Client that registered in app,
 *            Added elo and level
 * @author      Paweł Krupski
 * @version     1.2
 */
public class Client {



    private String login;
    private String password;
    private String email;
    private int elo;
    private String level;
    private int lastLesson;
    private int stage;

    public Client( String login, String password, String email, String level, int elo, int lastLesson, int stage){
        this.login = login;
        this.password = password;
        this.email = email;
        this.elo = elo;
        this.level = level;
        this.lastLesson = lastLesson;
        this.stage = stage;
    }

    /**
     * Method that returns client's login
     * @return client's login
     */
    public String getLogin() { return login; }

    /**
     * Method that returns client's password
     * @return client's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method that returns client's e-mail
     * @return client's e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method that returns client's ELO points
     * @return client's ELO points
     */
    public int getElo() {
        return elo;
    }


    /**
     * Method that sets new ELO points value in Client object
     * @param newElo new ELO points value
     * @return client's ELO points
     */
    public int setElo(int newElo) {
         this.elo = newElo; return elo;
    }
    /**
     * Method that returns client's level
     * @return client's level
     */

    public String getLevel() {
        return level;
    }

    /**
     * Method that returns client's last lesson
     * @return client's last lesson
     */
    public int getLastLesson() {
        return lastLesson;
    }

    /**
     * Method that sets new lastLesson value in Client object
     * @param lessonNumber new number of lesson
     * @return client's last lesson
     */
    public int setLastLesson(int lessonNumber) {
        this.lastLesson = lessonNumber;
        return lastLesson;
    }

    /**
     * Method that returns client's stage
     * @return client's stage
     */
    public int getStage() {
        return stage;
    }

    /**
     * Method that sets new stage value in Client object
     * @param stageNumber  new number of stage
     * @return client's stage
     */
    public int setStage(int stageNumber) {
        this.stage = stageNumber;
        return stage;
    }


}
