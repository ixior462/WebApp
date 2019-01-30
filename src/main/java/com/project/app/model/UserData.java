package com.project.app.model;
/**
 *  Class used to transfer user data entered during registration
 * @author      Weronika Pawlak
 *  @version     1.0
 */

public class UserData {

    public String userName;
    public String password;
    public String matchingPassword;
    public String email;

    /**
     * Method that returns client's username
     * @return client's username
     * @author Weronika Pawlak
     */
    public String getUserName() {

        return userName;
    }
    /**
     * Method that returns client's email
     * @return client's email
     * @author Weronika Pawlak
     */
    public String getEmail() {

        return email;
    }
    /**
     * Method that returns client's matching password
     * @return client's matching password
     * @author Weronika Pawlak
     */
    public String getMatchingPassword() {

        return matchingPassword;
    }
    /**
     * Method that returns client's password
     * @return client's password
     * @author Weronika Pawlak
     */
    public String getPassword() {

        return password;
    }
    /**
     * Method that sets client's email
     * @param email value
     * @author Weronika Pawlak
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Method that sets client's matching password
     * @param matchingPassword value
     * @author Weronika Pawlak
     */
    public void setMatchingPassword(String matchingPassword) {

        this.matchingPassword = matchingPassword;
    }
    /**
     * Method that sets client's password
     * @param password value
     * @author Weronika Pawlak
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Method that sets client's username
     * @param userName value
     * @author Weronika Pawlak
     */
    public void setUserName(String userName) {

        this.userName = userName;
    }

}
