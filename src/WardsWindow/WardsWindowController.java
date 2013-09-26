package WardsWindow;

import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class WardsWindowController implements ActionListener {
    WardsWindowModel model;
    WardsWindowView view;

    WardsWindowController() {
        System.out.println ("Controller()");
    }
    public void actionPerformed(java.awt.event.ActionEvent e){
        System.out.println("Controller: acting on Model");
        model.incrementValue();
    }
    public void addModel(WardsWindowModel m){
        System.out.println("Controller: adding model");
        this.model = m;
    }
    public void addView(WardsWindowView v){
        System.out.println("Controller: adding view");
        this.view = v;
    }
    public void initModel(int x){
        model.setValue(x);
    }
}
