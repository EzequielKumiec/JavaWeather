package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.UserRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.UserResponseDTO;
import ar.edu.utn.frc.tup.lciii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> setCliente(UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.saveClient(userRequestDTO), HttpStatus.CREATED);
    }
}
