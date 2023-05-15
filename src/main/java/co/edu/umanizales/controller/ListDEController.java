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

    //adicionar led
    @PostMapping
    public ResponseEntity<ResponseDTO> addLed(Led led) {

        led.setState(false);
        led.setDateOn(null);
        led.setDateOff(null);

        // Agregar el nuevo objeto Led a la lista
        listDEService.addLed(led);

        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el led", null),
                HttpStatus.OK);
    }

    //encender un led por id
    @GetMapping(path ="/lightled/{id}")
    public ResponseEntity<ResponseDTO> lightLed(@PathVariable int id){
        listDEService.lightLed(id);
        return new ResponseEntity<>(new ResponseDTO(
                200,"se ha encendido el led",null), HttpStatus.OK);
    }

    //enceder todos los leds
    @GetMapping(path ="/lightallled")
    public ResponseEntity<ResponseDTO> lightAllLed(){
        listDEService.lightAllLed();
        return new ResponseEntity<>(new ResponseDTO(
                200,"se ha encendido todos los bombillos",null), HttpStatus.OK);
    }

    //apagar un led
    @GetMapping(path ="/offled/{id}")
    public ResponseEntity<ResponseDTO> offLed(@PathVariable int id){
        listDEService.offLed(id);
        return new ResponseEntity<>(new ResponseDTO(
                200,"se ha apagado el led",null), HttpStatus.OK);
    }

    //apgar todos los leds
    @GetMapping(path ="/offallled")
    public ResponseEntity<ResponseDTO> offAllLed(){
        listDEService.offAllLed();
        return new ResponseEntity<>(new ResponseDTO(
                200,"se ha apagado todos los bombillos",null), HttpStatus.OK);
    }

    //enceder y apagar los leds desde la mitad de la mista
    @GetMapping(path ="/lightledinmiddle")
    public ResponseEntity<ResponseDTO> lightLedInMiddle(){
        listDEService.lightLedInMiddle();
        return new ResponseEntity<>(new ResponseDTO(
                200,"se apagaron y encendiero los bombillos",null), HttpStatus.OK);
    }
    @GetMapping(path ="/resertled")
    public ResponseEntity<ResponseDTO> resertLed(){
        listDEService.resertLed();
        return new ResponseEntity<>(new ResponseDTO(
                200,"bombillos reseteados",null), HttpStatus.OK);
    }




}
