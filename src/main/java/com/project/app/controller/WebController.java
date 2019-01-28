package com.project.app.controller;

import com.project.app.model.Client;
import com.project.app.model.ClientsDataAccessor;
import com.project.app.model.DictionaryAccessor;
import com.project.app.model.Word;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WebController {
    /**
     * @author      Weronika Pawlak
     * @author      Weronika Jasiak
     * @author      Jakub Dziwiński
     * @info        Class used to handling requests
     * @version     1.0
     */

    @RequestMapping(value = "/index")
    public String showMainPage(){
        return "index";
    }
    @RequestMapping(value = "/contact")
    public String showContactPage(){
        return "contact";
    }
    @RequestMapping(value = "/menu")
    public String showMenuPage(){
        return "menu";
    }
    @RequestMapping(value = "/indexClient")
    public String showClientMainPage(HttpSession session, Model model){
        return "indexClient";
    }
    @RequestMapping(value = "/contactClient")
    public String showClientContactPage(){
        return "contactClient";
    }
    @RequestMapping(value = "/menuClient")
    public String showClientMenuPage(){
        return "menuClient";
    }
    @RequestMapping(value = "/user")
    public String showClientPage() {
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
    @RequestMapping(value = "/lessonMenu")
    public String lessonMenu(){
        return "lessonMenu";
    }
    @RequestMapping(value = "/nauka")
    public String showLearningPage(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "nauka";
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
    @RequestMapping(value = "/nauka2")
    public String showLearningPage2(@RequestParam("lesson") List<Integer> lesson , Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();
        model.addAttribute("words", dict.getWordsFromLesson(lesson.get(0)).toArray());
        model.addAttribute("level",lesson.get(1));
        return "nauka2";
    }
    @GetMapping(value = "/nauka3")
    public String showLearningPage3(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "nauka3";
    }
    @PostMapping(value = "/nauka3")
    public void setLessonAndStage(WebRequest request, HttpSession session) {
        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client c = parser.getClient((String) session.getAttribute("username"));
        c.setLastLesson(Integer.parseInt(request.getParameter("lesson")));
        c.setStage(Integer.parseInt(request.getParameter("stage")));
        parser.updateClientsLessonJSON(c);
        parser.updateClientsStageJSON(c);
        System.out.println("Lesson: "+request.getParameter("lesson")+" Stage: "+request.getParameter("stage")+" User: "+session.getAttribute("username"));
        System.out.println("Lesson: "+c.getLastLesson()+" Stage: "+c.getStage()+" User: "+session.getAttribute("username"));

    }
    @RequestMapping(value = "/nauka4")
    public String showLearningPage4(@RequestParam(value = "lesson") int lesson ,Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();

        model.addAttribute("words", dict.getWordsFromLesson(lesson).toArray());
        return "nauka4";
    }

}
