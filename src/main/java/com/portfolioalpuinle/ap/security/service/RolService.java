
package com.portfolioalpuinle.ap.security.service;

import com.portfolioalpuinle.ap.security.entity.Rol;
import com.portfolioalpuinle.ap.security.enums.RolNombre;
import com.portfolioalpuinle.ap.security.repository.IRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    IRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}