
package com.portfolioalpuinle.ap.security.repository;

import com.portfolioalpuinle.ap.security.entity.Rol;
import com.portfolioalpuinle.ap.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
