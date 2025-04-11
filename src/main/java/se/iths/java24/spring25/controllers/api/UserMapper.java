package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.entity.AuthProvider;

@Component
class UserMapper {
    UserDTO map(UserEntity userEntity) {
        if (userEntity == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setName(userEntity.getName());
        dto.setEmail(userEntity.getEmail());
        dto.setProvider(userEntity.getProvider().toString());
        dto.setProviderId(userEntity.getProviderId());
        return dto;
    }

    UserEntity map(UserDTO dto) {
        if (dto == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setName(dto.getName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(dto.getPassword());
        if (dto.getProvider() != null) {
            userEntity.setProvider(AuthProvider.valueOf(dto.getProvider()));
        } else {
            userEntity.setProvider(AuthProvider.LOCAL);
        }
        userEntity.setProviderId(dto.getProviderId());
        return userEntity;
    }
}

