package com.project.app.controller;

import com.project.app.gameauthorization.Elo;
import com.project.app.gameauthorization.EloTuple;
import com.project.app.gameauthorization.Game;
import com.project.app.gameauthorization.QueueAccessor;
import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import com.project.app.model.DictionaryAccessor;
import com.project.app.model.UserData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author      Weronika Pawlak
 * @author      Weronika Jasiak
 * @author      Jakub Dziwiński
 * @author      Paweł Krupski
 * @author      Dominika Kunc
 *         Class used to handling requests
 * @version     1.0
 */
@Controller
public class WebController {



    /*
        QueueAccessor -> outside method to grant to have only one instantiation od the queue
     */
    QueueAccessor queue = QueueAccessor.getInstance();

    /**
     *  Method that checks sets lesson and stage for user
     * @author Jakub Dziwiński
     * @param request request containing values of lesson and stage
     * @param session session containing username
     */
    static void setLessonAndStage(WebRequest request, HttpSession session){
        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client c = parser.getClient((String) session.getAttribute("username"));
        int lesson = Integer.parseInt(request.getParameter("lesson"));
        int stage = Integer.parseInt(request.getParameter("stage"));
        if(c.getLastLesson()==lesson){
            if(c.getStage()<stage){
                c.setStage(stage);
                parser.updateClientsStageJSON(c);
            }
        }
        else if(c.getLastLesson()<lesson) {
            c.setLastLesson(lesson);
            c.setStage(stage);
            parser.updateClientsLessonJSON(c);
            parser.updateClientsStageJSON(c);
        }
    }

    @RequestMapping(value = "/index")
    public String showMainPage(HttpSession session,Model model){
        model.addAttribute("errorMessage", "");
        if(session.getAttribute("username")!=null)
        {
            String player = session.getAttribute("username").toString();

            queue.removeFromQueue(player);
        }


        return "index";
    }
    @RequestMapping(value = "/contact")
    public String showContactPage(){
        return "contact";
    }
    @PostMapping(value = "/contact")
    public String showContactPagePost(WebRequest request){
        System.out.println(request.getParameter("topic"));
        return "contact";
    }
    @RequestMapping(value = "/menu")
    public String showMenuPage(){
        return "menu";
    }
    @RequestMapping(value = "/indexClient")
    public String showClientMainPage(HttpSession session, Model model){
        if(session.getAttribute("username")!=null)
        {
            String player = session.getAttribute("username").toString();

            queue.removeFromQueue(player);
        }
        return "indexClient";
    }
    @RequestMapping(value = "/contactClient")
    public String showClientContactPage(HttpSession session){
        if(session.getAttribute("username")!=null)
        {
            String player = session.getAttribute("username").toString();

            queue.removeFromQueue(player);
        }
        return "contactClient";
    }

    @RequestMapping(value = "/menuClient")
    public String showClientMenuPage(HttpSession session, Model model){
        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client c = parser.getClient((String) session.getAttribute("username"));
        System.out.println(c.getLastLesson());
        model.addAttribute("lessons", c.getLastLesson());
        return "menuClient";
    }
    @RequestMapping(value = "/user")
    public String showClientPage(HttpSession session, Model model) {
        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client c = parser.getClient((String) session.getAttribute("username"));
        int k = parser.getClientRanking((String) session.getAttribute("username"));
        model.addAttribute("level", c.getLevel());
        model.addAttribute("ranking", k+1);
        model.addAttribute("username", session.getAttribute("username"));
        return "user";
    }
    @RequestMapping(value = "/specialists")
    public String showspecialistsPage() {
        return "specialists";
    }
    @RequestMapping(value = "/specialistsClient")
    public String showClientspecialistsPage() {
        return "specialistsClient";
    }
    @GetMapping(value = "/lessonMenu")
    public String lessonMenu(HttpSession session, Model model, @RequestParam(value = "lesson") int lesson ){
        DictionaryAccessor dict = new DictionaryAccessor();
        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client c = parser.getClient((String) session.getAttribute("username"));

        model.addAttribute("lastLesson", c.getLastLesson());
        model.addAttribute("stage", c.getStage());
        model.addAttribute("topic", dict.getTopicOfLesson(lesson));
        return "lessonMenu";
    }
    @RequestMapping(value = "/nauka")
    public String showLearningPage(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "nauka";
    }
    @PostMapping(value = "/nauka")
    public void setLessonAndStage1(WebRequest request, HttpSession session) {
        setLessonAndStage(request, session);
    }
    @RequestMapping(value = "/naukav2")
    public String showLearningPagev2(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "naukav2";
    }
    @RequestMapping(value = "/menu2")
    public String showLearningMenu(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "menu2";
    }
    @RequestMapping(value = "/rivalGame1")
    public String showRival(Model model){
        DictionaryAccessor dict = new DictionaryAccessor();
        model.addAttribute("level",2);
        model.addAttribute("words", dict.getWordsFromLevel("A1",5).toArray());
        return "rivalGame2";
    }
    @RequestMapping(value = "/nauka2")
    public String showLearningPage2(@RequestParam("lesson") List<Integer> lesson , Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();
        model.addAttribute("words", dict.getWordsFromLesson(lesson.get(0)).toArray());
        model.addAttribute("level",lesson.get(1));
        model.addAttribute("leveloflesson", dict.getLevelOfLesson(lesson.get(0)));
        return "nauka2";
    }
    @PostMapping(value = "/nauka2")
    public void setLessonAndStage2(WebRequest request, HttpSession session) {
        setLessonAndStage(request, session);
    }
    @GetMapping(value = "/nauka3")
    public String showLearningPage3(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "nauka3";
    }
    @PostMapping(value = "/nauka3")
    public void setLessonAndStage3(WebRequest request, HttpSession session) {
        setLessonAndStage(request, session);
        //System.out.println("Lesson: "+request.getParameter("lesson")+" Stage: "+request.getParameter("stage")+" User: "+session.getAttribute("username"));


    }
    @RequestMapping(value = "/nauka4")
    public String showLearningPage4(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "nauka4";
    }
    @PostMapping(value = "/nauka4")
    public void setLessonAndStage4(WebRequest request, HttpSession session) {
        setLessonAndStage(request, session);
    }




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

                model.addAttribute("username", user.userName);
                session.setAttribute("username", user.userName);
                return "redirect:indexClient";
            }
            else
            {
                System.out.println("Wrong password");
                model.addAttribute("errorMessage", "Błędny login i/lub hasło.");



            }
        }
        else
        {
            System.out.println("No user with login:  "+user.getUserName());
            model.addAttribute("errorMessage", "Błędny login i/lub hasło.");


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
            Client newClient = new Client(user.getUserName(), user.getPassword(), user.getEmail(), "A1", 1200, 1, 1);
            parser.addNewClientToJSON(newClient);
            model.addAttribute("username", user.userName);
            session.setAttribute("username", user.userName);
            return "redirect:indexClient";
        }
        else{
            model.addAttribute("errorMessage", "Wybrana nazwa użytkownika jest zajęta.");

        }
        return "redirect:register";
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


    @RequestMapping("/waiting")
    public String waitingPage(HttpSession session){

        String player = session.getAttribute("username").toString();
        Game game;
        if(!queue.isInQueue(player) ) { //   new player wants to play, add him to queue, refresh site and wait for match for him :>

            if(!queue.isInGame(player)) {
                System.out.println("Użytkownik = '" + player + "' czeka na rozgrywke");
                queue.addToQueue(player);
            }
            else{

                while(true) {

                    if (queue.getGameByNameOfPlayer(player) != null) {
                        return "redirect:rival_mode";

                    }
                }
            }
        }
        else{   //player is waiting for a game to start :>

            while(true){
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(player);
                if(queue.twoElementsAtQueue()){
                    System.out.println("Gracz "+player+" zaczyna grę");
                    game = queue.startGame();
                    queue.addNewGame(game);

                    //wpisz gre do arraylisty gier
                }
                if(!queue.isInQueue(player)){

                    return "redirect:rival_mode";


                }
            }
        }
        return "waiting";


    }

    @RequestMapping(value = "/back")
    public String backToClientMainPage(HttpSession session){
        String player = session.getAttribute("username").toString();
        queue.removeFromQueue(player);
        return "redirect:indexClient";
    }

    @RequestMapping("/rival_mode")
    public String rivalMode(HttpSession session, Model model){
        String player = (String) session.getAttribute("username");

        Game currentGame =  queue.getGameByNameOfPlayer(player);
        session.setAttribute("game",currentGame );

        System.out.println(currentGame.getWordsFromGame());
        int type = currentGame.getType();
        if(type==1)
        {
            //łączenie w pary

            model.addAttribute("words", currentGame.getWordsFromGame().toArray());
            return "rivalGame2";
        }
        else
        {
            //rozsypanka
            model.addAttribute("words", currentGame.getWordsFromGame().toArray());
            return "rivalGame1";
        }
    }



    @RequestMapping("/waitingResults")
    public String waitForResults(WebRequest request, HttpSession session){

        String player = session.getAttribute("username").toString();

        if(queue.isInGame(player) ){

            int point = Integer.parseInt(request.getParameter("points")); //Piots gained
            queue.getGameByNameOfPlayer(player).setPoints(player,point);
            System.out.println("Punkty gracza "+player+": "+point);
            queue.removeCurrentGame(player);
            queue.removeTimer(player);
            return  "waitingResults";
        }
        else{

            System.out.println("Player "+player+ " is waiting for player 2");

            while(true){
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                String secondPlayer = queue.getGameByNameOfPlayer(player).getSecondPlayer(player);
                if(queue.isInGame(secondPlayer) ) {

                    if(45 < queue.getTime(secondPlayer)){
                        queue.removeTimer(secondPlayer);
                        int point = 0;
                        queue.getGameByNameOfPlayer(secondPlayer).setPoints(secondPlayer,point);
                        System.out.println("Punkty gracza "+secondPlayer+": "+point);
                        queue.removeCurrentGame(secondPlayer);
                    }
                    else{
                        queue.addTimeToTimer(secondPlayer);
                        System.out.println(queue.getTime(secondPlayer));
                    }
                }
                else{
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

                        String info = "";

                        String winner = currentGame.getPlayer(1);
                        String player1 = currentGame.getPlayer(1);
                        String player2 = currentGame.getPlayer(2);
                        String loser;

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

                        if (Objects.equals(winner, player))
                            info = "Wygrana - zdobyte punkty rankingowe:  " + (tuple.WinnerElo - currentWinner.getElo());
                        else
                            info = "Przegrana - stracone punkty rankingowe:  " + (currentLoser.getElo() - tuple.LoserElo);

                        session.setAttribute("message", info);




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

                        String info = "";

                        String winner = currentGame.getPlayer(2);
                        String player1 = currentGame.getPlayer(1);
                        String player2 = currentGame.getPlayer(2);
                        String loser;

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

                        if (Objects.equals(winner, player))
                            info = "Wygrana - zdobyte punkty rankingowe: " + (tuple.WinnerElo - currentWinner.getElo());
                        else
                            info = "Przegrana - stracone punkty rankingowe: " + (currentLoser.getElo() - tuple.LoserElo);
                        session.setAttribute("message", info);

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

                        String info = "Remis - zdobyte punkty rankingowe: 0";
                        session.setAttribute("message", info);
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
        String message = (String) session.getAttribute("message");
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

        model.addAttribute("message", message);
        model.addAttribute("player1", player1);
        model.addAttribute("player2", player2);
        model.addAttribute("player1points", player1points);
        model.addAttribute("player2points", player2points);
        model.addAttribute("winner", winner);

        System.out.println("Player "+player1+" points: "+player1points+" Player "+player2+" points: "+player2points+" -----> Winner: "+winner);

        return "rival_results";
    }



}
