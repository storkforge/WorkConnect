package se.iths.java24.spring25.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.net.URI;

@Component
public class BrowserController implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String url = "http://localhost:8080";

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(url));
        } else {
            System.out.println("Öppna manuellt: " + url);
        }
    }
}
