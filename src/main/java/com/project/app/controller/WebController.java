package com.project.app.controller;

import com.project.app.model.DictionaryAccessor;
import com.project.app.model.Word;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
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
    public String showLearningPage2(Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();
        int numberOfWords = 4;
        Word[] words = new Word[numberOfWords];
        for(int i=0; i<numberOfWords; i++){
            Word w = dict.getRandomWord();
            words[i] = w;
        }
        model.addAttribute("words", words);
        return "nauka2";
    }
}
