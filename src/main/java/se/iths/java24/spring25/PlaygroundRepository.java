package se.iths.java24.spring25;

import org.springframework.data.repository.ListCrudRepository;
import se.iths.java24.spring25.domain.entity.Playground;

import java.util.List;

public interface PlaygroundRepository extends ListCrudRepository<Playground, Integer> {

    List<Playground> findByName(String name);


}
