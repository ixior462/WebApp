package com.project.app.registration;


public class UserData {
    /**
     * @author      Weronika Pawlak
     * @info        Class used to transfer user data entered during registration
     * @version     1.0
     */
    public String userName;
    public String password;
    public String matchingPassword;
    public String email;

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
