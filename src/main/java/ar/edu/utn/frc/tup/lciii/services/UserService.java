package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.UserRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.UserResponseDTO;
import ar.edu.utn.frc.tup.lciii.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    UserResponseDTO saveClient(UserRequestDTO userRequestDTO);
    Optional<UserEntity> getUserById(Long userId);
}
