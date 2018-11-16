package com.project.app.model;

public class Client {

    /**
     * @author      Paweł Krupski
     * @info        Class that represents each single Client that registered in app
     * @version     1.0
     */

    private String login;
    private String password;

    public Client( String login, String password){
        this.login = login;
        this.password = password;

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
