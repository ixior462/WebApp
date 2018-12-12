package com.project.app.gameauthorization;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;

public class Game {

    private Client player_1;
    private Client player_2;
    private ClientsDataAccessor accessor;

    public void setPlayers(Client player_1, Client player_2){
        accessor = new ClientsDataAccessor();
        this.player_1 = player_1;
        this.player_2 = player_2;
    }

    public void startGame(){
        System.out.println("GAME -> "+player_1.getLogin() +" VS "+player_2.getLogin());
    }
}
