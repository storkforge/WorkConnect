package se.iths.java24.spring25.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
public class LanguageController {

    private final LocaleResolver localeResolver;

    public LanguageController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @GetMapping("/change-language")
    public String changeLanguage(HttpServletRequest request, HttpServletResponse response, String lang) {
        Locale newLocale = new Locale(lang);
        localeResolver.setLocale(request, response, newLocale);

        // Hämta referer-headern för att få den tidigare sidan
        String referer = request.getHeader("Referer");

        // Om referer finns, återvänd till den sidan, annars till startsidan
        return (referer != null) ? "redirect:" + referer : "redirect:/";
    }
}
