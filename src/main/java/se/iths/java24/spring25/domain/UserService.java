package se.iths.java24.spring25.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.dto.UserDTO;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDto) {
        UserEntity user = userRepository.save(userMapper.map(userDto));
        return userMapper.map(user);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::map);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::map).toList();
    }

    public UserDTO updateUser(UserDTO userDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteUserById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
