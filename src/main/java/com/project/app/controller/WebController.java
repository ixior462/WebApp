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
     *         Class used to handling requests
     * @version     1.0
     */


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
    public String showMainPage(Model model){
        model.addAttribute("errorMessage", "");

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
    public String showClientMenuPage(HttpSession session, Model model){
        ClientsDataAccessor parser = new ClientsDataAccessor();
        Client c = parser.getClient((String) session.getAttribute("username"));
        System.out.println(c.getLastLesson());
        model.addAttribute("lessons", c.getLastLesson());
        return "menuClient";
    }
    @RequestMapping(value = "/user")
    public String showClientPage(HttpSession session, Model model) {
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

}
