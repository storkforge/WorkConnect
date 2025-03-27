package se.iths.java24.spring25.domain;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.dto.EventDTO;
import se.iths.java24.spring25.entity.EventEntity;

@Component
public class EventMapper {
    public EventDTO map(EventEntity eventEntity) {
        EventDTO dto = new EventDTO();
        // map all fields
        return dto;
    }

    public EventEntity map(EventDTO dto) {
        EventEntity eventEntity = new EventEntity();
        // map all fields
        return eventEntity;
    }
}
