package com.project.app.registration;

import com.project.app.gameauthorization.QueueAccessor;
import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.concurrent.TimeUnit;

@Controller
@Scope("session")
public class RegistrationController {


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
                return "redirect:mainpage";
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
        return "result";
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String mainpage(HttpSession session, Model model){


        System.out.println("principal name: "+session.getAttribute("username"));

        
        model.addAttribute("username", session.getAttribute("username"));


        return "mainpage";
    }
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
                    queue.startGame();
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
    @PostMapping("/rival_results")
    public String rivalResults(){
        return "rival_results";
    }


}

