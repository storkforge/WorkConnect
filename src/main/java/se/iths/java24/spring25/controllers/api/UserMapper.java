package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.UserEntity;

@Component
class UserMapper {
    UserDTO map(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        // map all fields
        return dto;
    }

    UserEntity map(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        // map all fields
        return userEntity;
    }
}

