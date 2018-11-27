package com.project.app.registration;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import com.project.app.model.ClientsJSONHandler;
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

                return "result";
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
        return "result";
    }
    @PostMapping("/register")
    public String NewUser(@ModelAttribute UserData user, Model model) {
        user.setUserName(user.getUserName());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(user.getPassword().equals(user.getMatchingPassword()))
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        }
        Client newClient = new Client(user.getUserName(),user.getPassword(),user.getEmail(),"A1", 1200);
        ClientsDataAccessor parser = new ClientsDataAccessor();
        parser.addNewClientToJSON(newClient);
        model.addAttribute("username", user.userName);
        return "result";
    }


}

