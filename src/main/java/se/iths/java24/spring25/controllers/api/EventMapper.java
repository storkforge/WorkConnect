package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.EventEntity;

@Component
class EventMapper {

    EventDTO map(EventEntity eventEntity) {
        return new EventDTO(
                eventEntity.getId(),
                eventEntity.getName(),
                eventEntity.getDate()
        );
    }

    EventEntity map(EventDTO dto) {
        EventEntity eventEntity = new EventEntity();
        // Don't map the id
        eventEntity.setName(dto.name());
        eventEntity.setDate(dto.date());
        return eventEntity;
    }
}

