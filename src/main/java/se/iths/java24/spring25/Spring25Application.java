package se.iths.java24.spring25;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


import java.awt.*;
import java.net.URI;
@EnableCaching
@SpringBootApplication
public class Spring25Application {
    public static void main(String[] args) {
        SpringApplication.run(Spring25Application.class, args);
        openHomePage();

    }


    private static void openHomePage() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI("http://localhost:8080"));
                System.out.println("🌍 Webbläsaren har öppnats på http://localhost:8080");
            }
        } catch (Exception e) {
            System.err.println("⚠️ Kunde inte öppna webbläsaren automatiskt: " + e.getMessage());
        }

    }
}
