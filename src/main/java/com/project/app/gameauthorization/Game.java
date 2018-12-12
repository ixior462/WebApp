package com.project.app.gameauthorization;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;

public class Game {

    private Client player_1;
    private Client player_2;
    private int player_1_points = -1;
    private int player_2_points = -1;
    private ClientsDataAccessor accessor;

    public void setPlayers(Client player_1, Client player_2){
        accessor = new ClientsDataAccessor();
        this.player_1 = player_1;
        this.player_2 = player_2;
    }

    public void setPoints(String player, int playerPoints)
    {
        if(player_1.getLogin().equals(player))
        {
            player_1_points = playerPoints;
        }
        else
        {
            player_2_points = playerPoints;

        }
    }

    public int getPoints(int player)
    {
        if(player == 1)
        {
            return  player_1_points;
        }
        else
        {
            return  player_2_points;

        }
    }

    public String getPlayer(int player)
    {
        if(player == 1)
        {
            return  player_1.getLogin();
        }
        else
        {
            return  player_2.getLogin();

        }
    }

    public void startGame(){
        System.out.println("GAME -> "+player_1.getLogin() +" VS "+player_2.getLogin());
    }

    public String getSecondPlayer(String player) {

        if(player_1.getLogin().equals(player))
        {
            return player_2.getLogin();
        }
        else
        {
            return player_1.getLogin();
        }
    }
}
