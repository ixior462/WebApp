package com.project.app.model;

import java.util.ArrayList;
/**
 *  Class that allows to get access to clients database
 * @author      Paweł Krupski
 * @version     1.0
 */

public class ClientsDataAccessor {
    //Class where we access Client Logs, Pass and Statistics
    private ClientsJSONHandler handler;



    public ClientsDataAccessor() {
        this.handler = new ClientsJSONHandler();
    }

    /**
     *  Method that adds new Client to JSON file.
     * @author Paweł Krupski
     * @param client Client object to be added
     *
     */
    public void addNewClientToJSON(Client client){
        handler.addClientToJSON(client);
    }

    /**
     *  Method that updates ELO points of specified Client in JSON File.
     * @author Dominika Kunc
     * @param client Client object to be updated
     *
     */
    public void updateClientsEloJSON(Client client){
        handler.updateEloInJSON(client.getLogin(), client.getElo());
    }

    /**
     *  Method that updates Lesson of specified Client in JSON File.
     * @author Dominika Kunc
     * @param client Client object to be updated
     *
     */
    public void updateClientsLessonJSON(Client client){ handler.updateLessonInJSON(client.getLogin(), client.getLastLesson()); }

    /**
     *  Method that updates stage of specified Client in JSON File.
     * @author Dominika Kunc
     * @param client Client object to be updated
     *
     */
    public void updateClientsStageJSON(Client client){ handler.updateStageInJSON(client.getLogin(), client.getStage()); }

    /**
     *  Method that returns ArrayList of Clients containing all Clients from JSON file and sorted in descending order.
     * @author Dominika Kunc
     * @return ArrayList of Clients ranking
     */
    public ArrayList<Client> getRanking(){
        return handler.getRankingFromJSON();
    }

    /**
     *  Method that returns ArrayList of Clients containing all Clients from JSON file and sorted in descending order.
     * @author Dominika Kunc
     * @param username username of client
     * @return Client's place in ranking
     */
    public int getClientRanking(String username){
        return handler.getClientRankingFromJSON(username);
    }


    /**
     *  Method that returns Client with specified login form JSON file.
     * @author Paweł Krupski
     * @param login login of wanted Client
     * @return Client client
     */
    public Client getClient(String login){
        return handler.getClientFromJSON(login);
    }

    /**
     *  Method that checks if specified username exists in JSON file.
     * @author Paweł Krupski
     * @param name username to be checked
     * @return true if exist false if not
     */
    public boolean usernameAlreadyExist(String name){
        return handler.usernameAlreadyExist(name);
    }

}
