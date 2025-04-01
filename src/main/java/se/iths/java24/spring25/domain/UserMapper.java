package se.iths.java24.spring25.domain;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.dto.EventDTO;
import se.iths.java24.spring25.dto.UserDTO;
import se.iths.java24.spring25.entity.EventEntity;
import se.iths.java24.spring25.entity.UserEntity;

@Component
public class UserMapper {
    public UserDTO map(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        // map all fields
        return dto;
    }

    public UserEntity map(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        // map all fields
        return userEntity;
    }
}

