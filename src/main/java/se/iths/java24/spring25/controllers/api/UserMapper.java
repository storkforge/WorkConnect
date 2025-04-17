package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.UserEntity;

@Component
class UserMapper {
    UserDTO map(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }

    UserEntity map(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.name());
        userEntity.setEmail(dto.email());
        return userEntity;
    }
}