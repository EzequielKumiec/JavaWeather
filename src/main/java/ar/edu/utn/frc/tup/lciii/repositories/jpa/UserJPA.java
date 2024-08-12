package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<UserEntity,Long> {
}
