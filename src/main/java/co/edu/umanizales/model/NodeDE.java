package co.edu.umanizales.model;


import lombok.Data;

@Data

public class NodeDE {
    private Led data;
    private NodeDE next;
    private NodeDE previ;

    public NodeDE(Led data){
        this.data=data;
    }
}
