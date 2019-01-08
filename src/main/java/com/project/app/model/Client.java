package com.project.app.model;

public class Client {

    /**
     * @author      Paweł Krupski
     * @info        Class that represents each single Client that registered in app,
     *              Added elo and level
     * @version     1.2
     */

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

    public String getLogin() { return login; }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getElo() {
        return elo;
    }
    public int setElo(int newElo) {
         this.elo = newElo; return elo;
    }

    public String getLevel() {
        return level;
    }

    public int getLastLesson() {
        return lastLesson;
    }

    public int getStage() {
        return stage;
    }

}
