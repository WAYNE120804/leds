package co.edu.umanizales.model;


import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public ArrayList<Led> printList() {
        ArrayList<Led> leds = new ArrayList<>();
        if (this.head != null) {
            NodeDE temp = this.head;
            do {
                leds.add(temp.getData());
                temp = temp.getNext();
            } while (temp != null);
        }
        return leds;
    }

    public void addLed(Led led) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            NodeDE newNode = new NodeDE(new Led(false, null, null));
            temp.setNext(newNode);
            newNode.setPrevi(temp);
        } else {
            NodeDE newNode = new NodeDE(new Led(false, null, null));
            setHead(newNode);
        }
        size++;
    }

    public void addToStartLed(Led led) {
        NodeDE newNode = new NodeDE(led);
        if (this.head != null) {
            this.head.setPrevi(newNode);
        }
        this.head = newNode;
        size++;
    }

    //resetear
    public void resertLed() {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                temp.getData().setState(false);
                temp.getData().setDateOff(null);
                temp.getData().setDateOn(null);
                temp = temp.getNext();
            }
        }
    }

    //encender  un led
    /*
    busco el bombillo por el id que quiero encender, cuando lo encuentre cambio su estado a true para que encienda
    y si actulizo si dateOn y su dateOff la pongo nulo
     */

    public void lightLed(int id) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getData().getId() != id) {
                temp = temp.getNext();
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

    public void lightAllLed() {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                temp.getData().setState(true);
                temp.getData().setDateOn(LocalTime.now());
                temp = temp.getNext();
            }
        }
    }

    //pegar  un led
    /*
    busco el bombillo por el id que quiero apagar, cuando lo encuentre cambio su estado a false para que apague
    y  actulizo su dateOff
     */

    public void offLed(int id) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getData().getId() != id) {
                temp = temp.getNext();
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
    public void offAllLed() {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                temp.getData().setState(false);
                temp.getData().setDateOff(LocalTime.now());
                temp = temp.getNext();
            }
        }
    }


    // pararme en la mitad de la lista y empezar a prender los leds
    /*
    Pregunto si la lista tiene datos

    si los tiene

    si la lista solo tiene a cabeza prendo a cabeza espero un seguno y la apago, actulizando su hora de encenido y
    apagado
    el tamaño lo divido entre dos y compruebo si genera si el numero es par o impar.

    si es par recorro la lista hasta llegar a la mitad y pongo el primer temporal en ese nodo y el segundo temporal lo
    pongo el nodo siguiente, despues digo que minetras previous y next sean difernetes anulos los temporales reocrra
    cada nodo prendan, actualicen la hora de encenidido, que esperen un segundo y lo apguen y actulicen la hora
    de apgado asi hasta llegar a los extremos.

    si la lista es impar hago que los dos temporales se paren en el nodo de la mitad de la lista  y hago que la recorran
    hasta que previous y next sean diferentes a nulos.

     */

    public void lightLedInMiddle() {

        if (this.head!=null) {
            if (this.head.getNext() != null) {


                if (size % 2 == 0) {
                    NodeDE temp = this.head;
                    NodeDE temp2 = this.head;
                    int middle = size / 2;
                    for (int i = 0; i < middle; i++) {
                        temp = temp.getNext();

                    }
                    temp2 = temp.getPrevi();
                    while (temp2 != head) {
                        temp2.getData().setState(true);
                        temp2.getData().setDateOn(LocalTime.now());
                        temp.getData().setState(true);
                        temp.getData().setDateOn(LocalTime.now());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        temp2.getData().setState(false);
                        temp2.getData().setDateOff(LocalTime.now());
                        temp.getData().setState(false);
                        temp.getData().setDateOff(LocalTime.now());

                        temp2 = temp2.getPrevi();
                        temp = temp.getNext();
                    }
                    this.head.getData().setState(true);
                    this.head.getData().setDateOn(LocalTime.now());
                    temp.getData().setState(true);
                    temp.getData().setDateOn(LocalTime.now());
                } else {
                    NodeDE temp = this.head;
                    NodeDE temp2 = this.head;
                    int middle = size + 1;
                    middle = middle / 2;
                    for (int i = 1; i < middle; i++) {
                        temp = temp.getNext();

                    }
                    temp2 = temp;
                    while (temp2 != this.head) {
                        temp2.getData().setState(true);
                        temp2.getData().setDateOn(LocalTime.now());
                        temp.getData().setState(true);
                        temp.getData().setDateOn(LocalTime.now());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        temp2.getData().setState(false);
                        temp2.getData().setDateOff(LocalTime.now());
                        temp.getData().setState(false);
                        temp.getData().setDateOff(LocalTime.now());

                        temp2 = temp2.getPrevi();
                        temp = temp.getNext();
                    }
                    this.head.getData().setState(true);
                    this.head.getData().setDateOn(LocalTime.now());
                    temp.getData().setState(true);
                    temp.getData().setDateOn(LocalTime.now());

                }
            } else {
                this.head.getData().setState(true);
                this.head.getData().setDateOn(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.head.getData().setState(false);
                this.head.getData().setDateOff(LocalTime.now());
            }
        }
    }

    /*

    public void lightLedInMiddle() {
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

     */




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

    /*
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

 */

}
