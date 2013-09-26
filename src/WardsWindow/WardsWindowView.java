package WardsWindow;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.lang.Integer;



/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class WardsWindowView implements Observer {
    private JTextField myTextField;
    private JButton button;

    WardsWindowView() {
        //frame in constructor and not an attribute as doesn't need to be visible to whole class
        JFrame frame = new JFrame("Nurse Rosters");
        frame.add("North", new JLabel("Wards"));

        myTextField = new JTextField();
        frame.add("Center", myTextField);

        //panel in constructor and not an attribute as doesn't need to be visible to whole class
        JPanel panel = new JPanel();
        button = new JButton("Test");
        panel.add(button);
        frame.add("South", panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        button.addActionListener(controller);
    }
}

