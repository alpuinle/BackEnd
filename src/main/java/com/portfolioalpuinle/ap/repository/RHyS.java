
package com.portfolioalpuinle.ap.repository;

import com.portfolioalpuinle.ap.entity.HyS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHyS extends JpaRepository<HyS, Integer>{
    Optional<HyS> findByNombre(String nombre);
    public boolean existsByNombre (String nombre);
}
