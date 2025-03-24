package se.iths.java24.spring25.infrastructure.persistence;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.iths.java24.spring25.domain.entity.Playground;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
@Testcontainers
class PlaygroundRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    PlaygroundRepository playgroundRepository;


    @BeforeEach
    void setUp() {
        var playground = new Playground();
        playground.setName("Playground");
        playgroundRepository.save(playground);
    }

    @Test
    void findAll() {
        var result = playgroundRepository.findAll();
        assertThat(result).hasSize(1);
    }

    @Test
    void findAll2() {
        var result = playgroundRepository.findAll();
        assertThat(result).hasSize(1);
    }

    @Test
    void findAllByNameReturnsOnlyPlaygroundWithMatchingName() {
        var playground = new Playground();
        playground.setName("test");
        playgroundRepository.save(playground);
        var result = playgroundRepository.findAllByName("test");
        assertThat(result).hasSize(1);
        assertThat(result)
                .hasSize(1)
                .extracting(Playground::getName)
                .containsExactly("test");

    }
}
