package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.EventEntity;

@Component
class EventMapper {

     EventDTO map(EventEntity eventEntity) {
        EventDTO dto = new EventDTO();
        // map all fields
        return dto;
    }

     EventEntity map(EventDTO dto) {
        EventEntity eventEntity = new EventEntity();
        // map all fields
        return eventEntity;
    }
}

