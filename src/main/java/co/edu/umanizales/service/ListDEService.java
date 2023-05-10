package co.edu.umanizales.service;


import co.edu.umanizales.model.Led;
import co.edu.umanizales.model.ListDE;
import co.edu.umanizales.model.NodeDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ListDEService {
    private ListDE leds;

    public  ListDEService(){leds=new ListDE();}

    public NodeDE getLeds(){return leds.getHead();}

    //mostral lista
    public String putToString(){return leds.toString();}

    //adicionar al final
    public void addLed(Led led) {leds.addLed(led);}

    //adicionar al comienzo
    public void addLedToStart(Led led){leds.addToStartLed(led);}

}