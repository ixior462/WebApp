package com.project.app.controller;

import com.project.app.model.DictionaryAccessor;
import com.project.app.model.Word;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
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
}
