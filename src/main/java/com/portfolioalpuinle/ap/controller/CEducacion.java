
package com.portfolioalpuinle.ap.controller;

import com.portfolioalpuinle.ap.dto.DtoEducacion;
import com.portfolioalpuinle.ap.entity.Educacion;
import com.portfolioalpuinle.ap.security.controller.Mensaje;
import com.portfolioalpuinle.ap.service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("educacion")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://frontend-alpuinle.web.app")
public class CEducacion {
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity <List<Educacion>> list(){
       List<Educacion> list = sEducacion.list();
       return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id")int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoeducacion){
       if(StringUtils.isBlank (dtoeducacion.getNombreE())){
         return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       }
       if(sEducacion.existsByNombreE(dtoeducacion.getNombreE())){
           return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
       }
       
       Educacion educacion = new Educacion(
                    dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE(), dtoeducacion.getFechaInicio(), dtoeducacion.getFechaFin());
       sEducacion.save(educacion);
       return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DtoEducacion dtoeducacion){
         if(!sEducacion.existsById(id)){
             return new ResponseEntity (new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
         }
         if(sEducacion.existsByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id) {
             return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
         }
         if(StringUtils.isBlank(dtoeducacion.getNombreE())){
             return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
         }
         
         Educacion educacion = sEducacion.getOne(id).get();
         
         educacion.setNombreE(dtoeducacion.getNombreE());
         educacion.setDescripcionE(dtoeducacion.getDescripcionE());
         educacion.setFechaInicio(dtoeducacion.getFechaInicio());
         educacion.setFechaFin(dtoeducacion.getFechaFin());
         
         sEducacion.save(educacion);
         
         return new ResponseEntity(new Mensaje("Educacion Actualizada"), HttpStatus.OK);
    }
}
