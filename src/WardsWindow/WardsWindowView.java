package WardsWindow;

import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.lang.Integer;


/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class WardsWindowView implements Observer {
    private TextField myTextField;
    private Button button;

    WardsWindowView() {
        System.out.println("View()");

        //frame in constructor and not an attribute as doesn't need to be visible to whole class
        Frame frame 		= new Frame("simple MVC");
        frame.add("North", new Label("counter"));

        myTextField 		= new TextField();
        frame.add("Center", myTextField);

        //panel in constructor and not an attribute as doesn't need to be visible to whole class
        Panel panel 		= new Panel();
        button	 		= new Button("PressMe");
        panel.add(button);
        frame.add("South", panel);

        frame.addWindowListener(new CloseListener());
        frame.setSize(200,100);
        frame.setLocation(100,100);
        frame.setVisible(true);
    }

    public void update(Observable obs, Object obj) {
        myTextField.setText("" + ((Integer)obj).intValue());
    }
    public void setValue(int v){
        myTextField.setText("" + v);
    }
    public void addController(WardsWindowController controller){
        System.out.println("View      : adding controller");
        button.addActionListener(controller);	//need controller before adding it as a listener
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}

