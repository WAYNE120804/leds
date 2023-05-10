package co.edu.umanizales.controller;


import co.edu.umanizales.controller.dto.ResponseDTO;
import co.edu.umanizales.model.Led;
import co.edu.umanizales.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping(path = "listde")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;


    @GetMapping(path ="/getlistde")
    public ResponseEntity<ResponseDTO> getLeds(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.putToString(),null), HttpStatus.OK);
    }

    //adicionar mascota
    @PostMapping
    public ResponseEntity<ResponseDTO> addLed(Led led) {

        // Establecer los valores de los atributos utilizando los métodos setters
        led.setState(false); // establecer el estado en "false"
        led.setDateOn(null); // el atributo "dataon" se establecerá más adelante cuando el LED se encienda
        led.setDateOff(LocalTime.now()); // establecer la hora actual como la hora en que se apagó el LED

        // Agregar el nuevo objeto Led a la lista
        listDEService.addLed(led);

        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el led", null),
                HttpStatus.OK);
    }



}
