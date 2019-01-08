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

    public void updateClientsEloJSON(Client client){
        handler.updateEloInJSON(client.getLogin(), client.getElo());
    }

    public Client getClient(String login){
        return handler.getClientFromJSON(login);
    }

    public boolean usernameAlreadyExist(String name){
        return handler.usernameAlreadyExist(name);
    }

}
