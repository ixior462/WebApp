package com.project.app.registration;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegistrationController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(WebRequest request, Model model) {
        UserData user = new UserData();
        model.addAttribute("user", user);

        return "login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "registration";
    }
    @PostMapping("/login")
    public String HelloUser(@ModelAttribute UserData user, Model model) {
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
    @RequestMapping(value = "/mainpage")
    public String mainpage(){
        return "mainpage";
    }
    @PostMapping("/register")
    public String NewUser(@ModelAttribute UserData user, Model model) {

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
        }
        else{
            //TODO: NOTIFY USER THAT NAME HE CHOOSE IS ALREADY TAKEN AND HE DIDN'T REGISTER

            // Right now, user only see: "Hello User: null"
        }
        return "result";
    }


}

