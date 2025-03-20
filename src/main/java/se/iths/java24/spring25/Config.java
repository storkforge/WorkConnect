package se.iths.java24.spring25;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.java24.spring25.domain.entity.Playground;
import se.iths.java24.spring25.infrastructure.persistence.PlaygroundRepository;

@Configuration
public class Config {
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
