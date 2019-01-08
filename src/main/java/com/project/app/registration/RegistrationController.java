package com.project.app.registration;

import com.project.app.gameauthorization.Elo;
import com.project.app.gameauthorization.EloTuple;
import com.project.app.gameauthorization.Game;
import com.project.app.gameauthorization.QueueAccessor;
import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Controller
@Scope("session")
public class RegistrationController {


     Game game;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(WebRequest request, Model model) {
        UserData user = new UserData();
        model.addAttribute("user", user);

        return "login";
    }

    @PostMapping("/login")
    public String HelloUser(@ModelAttribute UserData user, Model model, HttpSession session) {
        user.setUserName(user.getUserName());
        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client currentClient = parser.getClient(user.getUserName());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CharSequence rawPassword = user.getPassword();
        if(user.getPassword()==null)
        {
            System.out.println("Empty password");

        }
        if(currentClient!=null)
        {

            if(passwordEncoder.matches(rawPassword, currentClient.getPassword())==true)
            {
                System.out.println("Successful logged: "+currentClient.getLogin());
                /*
                TODO: Create page for successful log in
                 */
                model.addAttribute("username", user.userName);
                session.setAttribute("username", user.userName);
                return "redirect:indexClient";
            }
            else
            {
                System.out.println("Wrong password");



            }
        }
        else
        {
            System.out.println("No user with login:  "+user.getUserName());

        }
        model.addAttribute("username", user.userName);
        return "redirect:login";
    }


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register")
    public String NewUser(@ModelAttribute UserData user, Model model,HttpSession session) {

        ClientsDataAccessor parser = new ClientsDataAccessor();

        if(!parser.usernameAlreadyExist(user.getUserName())) {
            user.setUserName(user.getUserName());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (user.getPassword().equals(user.getMatchingPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));

            }
            Client newClient = new Client(user.getUserName(), user.getPassword(), user.getEmail(), "A1", 1200, 0, 0);
            parser.addNewClientToJSON(newClient);
            model.addAttribute("username", user.userName);
            session.setAttribute("username", user.userName);
        }
        else{
            //TODO: NOTIFY USER THAT NAME HE CHOOSE IS ALREADY TAKEN AND HE DIDN'T REGISTER

            // Right now, user only see: "Hello User: null"
        }
        return "redirect:indexClient";
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // PRZENIESIONE DO WEBCONTROLLER (MAPPING "/indexClient")

    /*@RequestMapping("/mainpage")
    public String mainpage(HttpSession session, Model model){
        System.out.println("principal name: "+session.getAttribute("username"));
        model.addAttribute("username", session.getAttribute("username"));
        return "indexClient";
    }*/
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*
        QueueAccessor -> outside method to grant to have only one instantiation od the queue
     */
    QueueAccessor queue = QueueAccessor.getInstance();

    @RequestMapping("/waiting")
    public String waitingPage(HttpSession session){

        String player = session.getAttribute("username").toString();
        if(!queue.isInQueue(player) ) { //   new player wants to play, add him to queue, refresh site and wait for match for him :>
            if(!queue.isInGame(player)) {
                System.out.println("Użytkownik = '" + player + "' czeka na rozgrywke");
                queue.addToQueue(player);
            }
            else{
                return "redirect:rival_mode";

            }
        }
        else{   //player is waiting for a game to start :>

            while(true){
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(player);
                if(queue.twoElementsAtQueue()){
                    System.out.println("Gracz "+player+" zaczyna grę");
                    queue.addNewGame(queue.startGame());
                    //wpisz gre do arraylisty gier
                }
                if(!queue.isInQueue(player)){
                    // TODO: Game starts
                    return "redirect:rival_mode";
                }
            }
        }
        return "waiting";


    }



    @RequestMapping("/rival_mode")
    public String rivalMode(){
        return "rival_mode";
    }



        @RequestMapping("/waitingResults")
    public String waitForResults(WebRequest request, HttpSession session){

        String player = session.getAttribute("username").toString();

        if(queue.isInGame(player) ){

            int point = Integer.parseInt(request.getParameter("points")); //Piots gained
            queue.getGameByNameOfPlayer(player).setPoints(player,point);
            System.out.println("Punkty gracza "+player+": "+point);
            queue.removeCurrentGame(player);
            return  "waitingResults";
        }
        else{

            System.out.println("Player "+player+ " is waiting for player 2");

            while(true){
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                String secondPlayer = queue.getGameByNameOfPlayer(player).getSecondPlayer(player);
                if(!queue.isInGame(secondPlayer) ){

                    Game currentGame =  queue.getGameByNameOfPlayer(player);

                    if(currentGame.getPoints(1) > currentGame.getPoints(2) )
                    {
                        System.out.println("Gracz "+ currentGame.getPlayer(1)+" wygrał");
                        session.setAttribute("winner", currentGame.getPlayer(1));
                        session.setAttribute("player1points", currentGame.getPoints(1));
                        session.setAttribute("player2points", currentGame.getPoints(2));
                        session.setAttribute("player1", currentGame.getPlayer(1));
                        session.setAttribute("player2", currentGame.getPlayer(2));
                        session.setAttribute("isUpdated", 0);




                    }
                    else if(currentGame.getPoints(1) < currentGame.getPoints(2) )
                    {
                        System.out.println("Gracz "+ currentGame.getPlayer(2)+" wygrał");
                        session.setAttribute("winner", currentGame.getPlayer(2));
                        session.setAttribute("player1points", currentGame.getPoints(1));
                        session.setAttribute("player2points", currentGame.getPoints(2));
                        session.setAttribute("player1", currentGame.getPlayer(1));
                        session.setAttribute("player2", currentGame.getPlayer(2));
                        session.setAttribute("isUpdated", 0);


                    }
                    else
                    {
                        System.out.println("Remis");
                        String winner = currentGame.getPlayer(1)+" i "+currentGame.getPlayer(2);
                        session.setAttribute("winner", winner);
                        session.setAttribute("player1points", currentGame.getPoints(1));
                        session.setAttribute("player2points", currentGame.getPoints(2));
                        session.setAttribute("player1", currentGame.getPlayer(1));
                        session.setAttribute("player2", currentGame.getPlayer(2));
                        session.setAttribute("isUpdated", 1);


                    }

                    return "redirect:rival_results";
                }
            }
        }





    }
    @RequestMapping("/rival_results")
    public String rivalResults(WebRequest request, HttpSession session, Model model){

        String player1 = (String) session.getAttribute("player1");
        String player2 = (String) session.getAttribute("player2");
        int player1points =  (Integer) session.getAttribute("player1points");
        int player2points = (Integer) session.getAttribute("player2points");
        String winner = (String) session.getAttribute("winner");

        String loser;
        if(!winner.equals(player1+" i "+player2) && player1.equals(session.getAttribute("username").toString())) {
            if (Objects.equals(winner, player1))
                loser = player2;
            else
                loser = player1;


            EloTuple tuple = new EloTuple();
            ClientsDataAccessor parser = new ClientsDataAccessor();
            Client currentWinner = parser.getClient(winner);
            Client currentLoser = parser.getClient(loser);

            tuple.WinnerElo = currentWinner.getElo();
            tuple.LoserElo = currentLoser.getElo();
            Elo elocalc = new Elo();
            tuple = elocalc.calculateEloRating(tuple);
            currentWinner.setElo(tuple.WinnerElo);
            currentLoser.setElo(tuple.LoserElo);
            parser.updateClientsEloJSON(currentWinner);
            parser.updateClientsEloJSON(currentLoser);
            System.out.println("Winner now has  "+tuple.WinnerElo+" elo points. Loser now has: "+tuple.LoserElo+" elo points");


        }



        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client pl1 = parser.getClient(player1);
        Client pl2 = parser.getClient(player2);

        model.addAttribute("player1", player1);
        model.addAttribute("player2", player2);
        model.addAttribute("player1points", player1points);
        model.addAttribute("player2points", player2points);
        model.addAttribute("winner", winner);

        System.out.println("Player "+player1+" points: "+player1points+" Player "+player2+" points: "+player2points+" -----> Winner: "+winner);

        return "rival_results";
    }


}

