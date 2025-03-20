package se.iths.java24.spring25;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.iths.java24.spring25.domain.entity.Playground;
import se.iths.java24.spring25.infrastructure.persistence.PlaygroundRepository;

@SpringBootApplication
public class Spring25Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring25Application.class, args);
    }

    @Bean
    CommandLineRunner runner(PlaygroundRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Playground playground = new Playground();
                playground.setName("Test playground");
                repository.save(playground);
            }
        };

    }
}
