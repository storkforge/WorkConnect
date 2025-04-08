package se.iths.java24.spring25.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(@RequestParam(value = "lang", required = false) String lang, Model model) {
        if (lang != null) {
            model.addAttribute("currentLang", new Locale(lang));
        }
        return "logout";  // Pekar på logout.html
    }
}
