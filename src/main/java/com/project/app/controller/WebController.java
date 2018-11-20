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
        String[] pl = new String[numberOfWords];
        String[] eng = new String[numberOfWords];
        for(int i=0; i<numberOfWords; i++){
            Word w = dict.getRandomWord();
            pl[i] = w.getPl();
            eng[i] = w.getEng();
        }

        model.addAttribute( "pl", pl);
        model.addAttribute("eng", eng);
        return "nauka";
    }
}
