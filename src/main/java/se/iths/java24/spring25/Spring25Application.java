package se.iths.java24.spring25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class Spring25Application {
    public static void main(String[] args) {
        SpringApplication.run(Spring25Application.class, args);

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
