package com.project.app.registration;


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
        model.addAttribute("username", user.userName);
        return "result";
    }
    @PostMapping("/register")
    public String NewUser(@ModelAttribute UserData user, Model model) {
        user.setUserName(user.getUserName());
        model.addAttribute("username", user.userName);
        return "result";
    }


}

