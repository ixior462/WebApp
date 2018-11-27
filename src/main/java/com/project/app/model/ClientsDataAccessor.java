package com.project.app.model;

public class ClientsDataAccessor {
    //Class where we access Client Logs, Pass and Statistics
    private ClientsJSONHandler handler;


    public ClientsDataAccessor() {
        this.handler = new ClientsJSONHandler();
    }

    public void addNewClientToJSON(Client client){
        handler.addClientToJSON(client);
    }

    public Client getClient(String login){
        return handler.getClientFromJSON(login);
    }
    public boolean clientAlreadyExist(String login){
        return handler.clientAlreadyExist(login);
    }
}
