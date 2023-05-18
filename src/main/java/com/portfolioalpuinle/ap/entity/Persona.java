
package com.portfolioalpuinle.ap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "El valor proporcionado no cumple con los requisitos de longitud")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 50, message = "El valor proporcionado no cumple con los requisitos de longitud")
    private String apellido;
    
    @Size(min = 1, max = 50, message = "El valor proporcionado no cumple con los requisitos de longitud")
    private String img;

    
}

