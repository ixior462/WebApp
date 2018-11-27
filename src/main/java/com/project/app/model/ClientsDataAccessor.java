package com.project.app.model;

public class ClientsDataAccessor {

    /**
     * @author      Paweł Krupski
     * @info        Class that allows to get access to client database 
     * @version     1.0
     */

    private ClientsJSONHandler handler;

    public ClientsDataAccessor() {
        this.handler = new ClientsJSONHandler();
    }

    public void addClienttoJSON(Client client){
        handler.addClientToJSON(client);
    }

    public Client getClient(String login){
        return handler.getClient(login);
    }

    public boolean clientAlreadyExist(String login){
        return handler.clientAlreadyExist(login);
    }
}
