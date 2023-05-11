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

    //resetear
    public void resertLed(){
        if (this.head!=null){
            NodeDE temp=this.head;
            while (temp!=null){
                temp.getData().setState(false);
                temp.getData().setDateOff(null);
                temp.getData().setDateOn(null);
                temp=temp.getNext();
            }
        }
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

    //prender led anteriror
    public void lightPreviousLed(NodeDE temp){
            while (temp!=null){
                temp.getPrevi().getData().setState(true);
                temp.getData().setDateOn(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                temp.getData().setState(false);
                temp.getData().setDateOff(LocalTime.now());
            }
    }

    //prender led siguiente
    public void lightNextLed(NodeDE temp){
        while (temp!=null){
            temp.getNext().getData().setState(true);
            temp.getData().setDateOn(LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp.getData().setState(false);
            temp.getData().setDateOff(LocalTime.now());
        }
    }

    // pararme en la mitad de la lista y empezar a prender los leds
    /*
    recorro la lista hasta estar en la mitad, cuando este en la mitad enciendo ese led, espero un segundo y lo apago
    despues paso al siguiente y al anterior con un segundo temporal que comienze desde ese nodo de la mitdad
    y hago lo mismo lo enciendo espero un segundo y
    los apago. asi consecutivamente
    hasta llegar al comienzo y final de la lista
     */

    public void lightLedInMiddle() {
        // 1. Comprobar que la lista no está vacía
        if (this.head == null) {
            return;
        }

            int middle = this.size / 2;
            NodeDE temp = this.head;
            for (int i = 0; i < middle; i++) {
                temp = temp.getNext();
            }

            NodeDE next = temp;
            while (next != null) {
                next.getData().setState(true);
                next.getData().setDateOn(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                next.getData().setState(false);
                next.getData().setDateOff(LocalTime.now());
                next = next.getNext();
            }

            NodeDE previ = temp.getPrevi();
            while (previ != null) {
                previ.getData().setState(true);
                previ.getData().setDateOn(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                previ.getData().setState(false);
                previ.getData().setDateOff(LocalTime.now());
                previ = previ.getPrevi();
            }

            next = temp.getNext();
            while (next != null) {
                next.getData().setState(true);
                next.getData().setDateOn(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                next.getData().setState(false);
                next.getData().setDateOff(LocalTime.now());
                next = next.getNext();
            }

            previ = temp.getPrevi();
            while (previ != null) {
                previ.getData().setState(true);
                previ.getData().setDateOn(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                previ.getData().setState(false);
                previ.getData().setDateOff(LocalTime.now());
                previ = previ.getPrevi();
            }
    }




    /* metodo donde si intento con hilos, no se dio porque ya que se demoraba mucho en ejecutar todo el metodo
    public void lightLedInMiddle() {
        if (this.head != null) {
            NodeDE temp = this.head;
            int middle = this.size / 2;
            int i = 1;

            while (temp.getNext() != null) {
                if (i == middle) {
                    // Encender el LED en la mitad de la lista
                    temp.getData().setState(true);
                    temp.getData().setDateOn(LocalTime.now());

                    // Crear los hilos para encender los LEDs anteriores y posteriores
                    NodeDE finalTemp = temp;
                    Thread prevThread = new Thread(() -> lightPreviousLed(finalTemp.getPrevi()));
                    NodeDE finalTemp1 = temp;
                    Thread nextThread = new Thread(() -> lightNextLed(finalTemp1.getNext()));

                    // Iniciar los hilos
                    prevThread.start();
                    nextThread.start();

                    // Esperar a que los hilos terminen antes de continuar
                    try {
                        prevThread.join();
                        nextThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Apagar el LED de la mitad de la lista
                    temp.getData().setDateOff(LocalTime.now());

                    break; // Salir del ciclo while
                }

                temp = temp.getNext();
                i++;
            }
        }
    }
     */

}
