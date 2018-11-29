package com.project.app.controller;

import com.project.app.model.DictionaryAccessor;
import com.project.app.model.Word;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = "/nauka")
    public String showLearningPage(Model model) {
        DictionaryAccessor dict = new DictionaryAccessor();
        int numberOfWords = 3;
        Word[] words = new Word[numberOfWords];
        for(int i=0; i<numberOfWords; i++){
            Word w = dict.getRandomWord();
            words[i] = w;
        }
        model.addAttribute("words", words);
        return "nauka";
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
