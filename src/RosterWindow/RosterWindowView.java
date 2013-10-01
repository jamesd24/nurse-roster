package RosterWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterWindowView extends JFrame
{
    private JButton closeButton = new JButton("Close");
    private JButton printButton = new JButton("Print");
    private JButton generateRosterButton = new JButton("Generate Roster");
    private JList rosterList = new JList();
    private JScrollPane rosterPane = new JScrollPane();

    RosterWindowView(){

        // Sets up the view and adds the components
        // Without using GUI Form designer (BOSS!)

        JPanel listPanel = new JPanel();
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.setTitle("{Ward} - Roster");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(880, 680);
        this.setResizable(false);


        rosterList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Generated Roster Goes Here"  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        rosterPane.setViewportView(rosterList);
        rosterPane.setPreferredSize(new Dimension(810, 540));

        listPanel.add(rosterPane);

        closeButton.setPreferredSize(new Dimension(120,30));
        printButton.setPreferredSize(new Dimension(120,30));
        generateRosterButton.setPreferredSize(new Dimension(140,30));

        topPanel.add(generateRosterButton);

        buttonsPanel.add(printButton);
        buttonsPanel.add(closeButton);

        listPanel.setPreferredSize(new Dimension(810,540));
        buttonsPanel.setPreferredSize(new Dimension(130,70));

        this.add("North", topPanel);
        this.add("Center",listPanel);
        this.add("South", buttonsPanel);

        // End of setting up the components --------

    }

    void addGenerateListener(ActionListener l){
        generateRosterButton.addActionListener(l);
    }
    void addPrintListener(ActionListener l){
        printButton.addActionListener(l);
    }
    void addCloseListener(ActionListener l){
        closeButton.addActionListener(l);
    }

    // Open a popup that contains the error message passed
    void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(this, errorMessage);

    }

}

