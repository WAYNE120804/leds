package co.edu.umanizales.model;


import lombok.Data;

import java.time.LocalTime;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        NodeDE temp = this.head;
        while (temp != null) {
            // Acceder al objeto Led almacenado en el nodo actual
            Led led = temp.getData();

            // Imprimir los valores de los atributos del objeto Led
            sb.append("ID: ").append(led.getId()).append("..");
            sb.append("Estado: ").append(led.isState()).append("..");
            sb.append("Fecha de encendido: ").append(led.getDateOn()).append("..");
            sb.append("Fecha de apagado: ").append(led.getDateOff()).append("----");


            // Mover el puntero al siguiente nodo
            temp = temp.getNext();
        }
        return sb.toString();
    }

    public void addLed(Led led)  {
        if (this.head!=null){
            NodeDE temp=this.head;
            while (temp.getNext()!=null){
                temp=temp.getNext();
            }
            NodeDE newNode= new NodeDE(new Led(false,null, LocalTime.now()));
            temp.setNext(newNode);
            newNode.setPrevi(temp);
        }else {
            NodeDE newNode=new NodeDE(led);
            setHead(newNode);
        }
        size++;
    }

    public void addToStartLed(Led led){
        NodeDE newNode=new NodeDE(led);
        if(this.head!=null){
            this.head.setPrevi(newNode);
        }
        this.head=newNode;
        size++;
    }


}
