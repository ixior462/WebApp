package com.project.app.registration;


public class UserData {
    private String userName;
    private String password;
    private String matchingPassword;
    private String email;

    public String getUserName() {

        return userName;
    }

    public String getEmail() {

        return email;
    }

    public String getMatchingPassword() {

        return matchingPassword;
    }

    public String getPassword() {

        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatchingPassword(String matchingPassword) {

        this.matchingPassword = matchingPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

}
