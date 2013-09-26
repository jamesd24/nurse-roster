package WardsWindow;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class WardsWindowModel extends Observable {

    private int counter;

    public WardsWindowModel(){
        System.out.println("Model()");

    }
    public void setValue(int value) {

        this.counter = value;
        System.out.println("Model init: counter = " + counter);
        setChanged();
        notifyObservers(counter);
    }
    public void incrementValue() {

        ++counter;
        System.out.println("Model     : counter = " + counter);
        setChanged();
        notifyObservers(counter);
    }
}
