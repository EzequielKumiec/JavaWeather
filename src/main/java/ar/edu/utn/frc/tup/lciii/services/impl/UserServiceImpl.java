package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.UserRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.UserResponseDTO;
import ar.edu.utn.frc.tup.lciii.entities.UserEntity;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.UserJPA;
import ar.edu.utn.frc.tup.lciii.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJPA userJPA;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDTO saveClient(UserRequestDTO userRequestDTO) {
        UserEntity user = modelMapper.map(userRequestDTO,UserEntity.class);
        user.setSecret(UUID.randomUUID().toString());
        UserEntity newUser = userJPA.save(user);

        return modelMapper.map(newUser,UserResponseDTO.class);
    }

    @Override
    public Optional<UserEntity> getUserById(Long userId) {
        return userJPA.findById(userId);
    }
}
