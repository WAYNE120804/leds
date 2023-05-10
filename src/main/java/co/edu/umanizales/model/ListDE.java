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
            sb.append("[{ID: ").append(led.getId()).append("}..");
            sb.append("{Estado: ").append(led.isState()).append("}..");
            sb.append("{Fecha de encendido: ").append(led.getDateOn()).append("}..");
            sb.append("{Fecha de apagado: ").append(led.getDateOff()).append("}]--");


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
            NodeDE newNode= new NodeDE(new Led(false,null, null));
            temp.setNext(newNode);
            newNode.setPrevi(temp);
        }else {
            NodeDE newNode=new NodeDE(new Led(false,null, null));
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

    //encender  un led
    /*
    busco el bombillo por el id que quiero encender, cuando lo encuentre cambio su estado a true para que encienda
    y si actulizo si dateOn y su dateOff la pongo nulo
     */

    public void lightLed(int id){
        if(this.head!=null){
            NodeDE temp=this.head;
            while (temp.getData().getId()!=id){
                temp=temp.getNext();
            }
            temp.getData().setState(true);
            temp.getData().setDateOn(LocalTime.now());
        }
    }

    //encender  todos los led
    /*
    me paro en cada  bombillo y cambio su estado a true para que encienda
    y  actulizo si dateOn y su dateOff la pongo nulo
     */

    public void lightAllLed(){
        if(this.head!=null){
            NodeDE temp=this.head;
            while (temp!=null){
                temp.getData().setState(true);
                temp.getData().setDateOn(LocalTime.now());
                temp=temp.getNext();
            }
        }
    }

    //pegar  un led
    /*
    busco el bombillo por el id que quiero apagar, cuando lo encuentre cambio su estado a false para que apague
    y  actulizo su dateOff
     */

    public void offLed(int id){
        if(this.head!=null){
            NodeDE temp=this.head;
            while (temp.getData().getId()!=id){
                temp=temp.getNext();
            }
            temp.getData().setState(false);
            temp.getData().setDateOff(LocalTime.now());

        }
    }

    //apgar todos los leds
    /*
    me paro en cada  bombillo y cambio su estado a false para que apgue
    y  actulizo  su dateOff
     */
    public void offAllLed(){
        if(this.head!=null){
            NodeDE temp=this.head;
            while (temp!=null){
                temp.getData().setState(false);
                temp.getData().setDateOff(LocalTime.now());
                temp=temp.getNext();
            }
        }
    }

    // pararme en la mitad de la lista y empezar a prender los leds
    /*
    recorro la lista hasta estar en la mitad, cuando este en la mitad enciendo ese led, espero un segundo y lo apago
    despues paso al siguiente y anterior y hago lo mismo lo enciendo espero un segundo y los apago. asi consecutivamente
    hasta llegar al comienzo y final de la lista
     */

    public void lightLedInMiddle(){
        if (this.head!=null){
            NodeDE temp=this.head;
            int middle=this.size/2;
            int i=1;
            while (temp!=null){
                if (i==middle){
                    temp.getData().setState(true);
                    temp.getData().setDateOn(LocalTime.now());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp.getData().setState(false);
                    temp.getData().setDateOff(LocalTime.now());

                    NodeDE prev=temp.getPrevi();
                    NodeDE next=temp.getNext();
                    while (prev !=null && next !=null){

                        prev.getData().setState(true);
                        prev.getData().setDateOn(LocalTime.now());

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        prev.getData().setState(false);
                        prev.getData().setDateOff(LocalTime.now());

                        next.getData().setState(true);
                        next.getData().setDateOn(LocalTime.now());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        next.getData().setState(false);
                        next.getData().setDateOff(LocalTime.now());


                        prev = prev.getPrevi();
                        next = next.getNext();
                    }

                    break;
                }

                temp = temp.getNext();
                i++;
            }
        }
    }



}
