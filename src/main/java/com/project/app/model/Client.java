package com.project.app.model;

public class Client {

    /**
     * @author      Paweł Krupski
     * @info        Class that represents each single Client that registered in app,
     *              Added elo and level
     * @version     1.1
     */

    private String login;
    private String password;
    private String email;
    private int elo;
    private String level;

    public Client( String login, String password, String email, String level, int elo){
        this.login = login;
        this.password = password;
        this.email = email;
        this.elo = elo;
        this.level = level;
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

    public String getLevel() {
        return level;
    }




}
