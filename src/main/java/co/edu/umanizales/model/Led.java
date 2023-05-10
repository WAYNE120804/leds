package co.edu.umanizales.model;




import java.time.LocalTime;

public class Led {
    private static int nextid=1;
    private int id ;
    private boolean state;
    private LocalTime dateOn;
    private LocalTime dateOff;

    public Led( boolean state, LocalTime dateOn, LocalTime dateOff) {
        this.id = nextid;
        nextid++;
        this.state = false;
        this.dateOn = null;
        this.dateOff = LocalTime.now();
    }

    public Led() {}

    public int getId() {
        return id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        if (state == true) {
            // si el LED se enciende, se establece la hora actual como la hora en que se encendió
            this.dateOn = LocalTime.now();
        } else {
            // si el LED se apaga, se establece la hora actual como la hora en que se apagó
            this.dateOff = LocalTime.now();
        }
    }

    public LocalTime getDateOn() {
        return dateOn;
    }

    public LocalTime getDateOff() {
        return dateOff;
    }

    public void setDateOn(LocalTime dateOn) {
        this.dateOn = dateOn;
    }

    public void setDateOff(LocalTime dateOff) {
        this.dateOff = dateOff;
    }

    public void setId(int id) {
        this.id = id;
    }
}


