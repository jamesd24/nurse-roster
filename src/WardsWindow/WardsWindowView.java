package WardsWindow;


/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
// This is the View
// Its only job is to display what the user sees
// It performs no calculations, but instead passes
// information entered by the user to whomever needs
// it.

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WardsWindowView extends JFrame{

    private JTextField firstNumber  = new JTextField(10);
    private JTextField secondNumber = new JTextField(10);
    private JButton newWardButton = new JButton("New Ward");
    private JButton propertiesButton = new JButton("Properties");
    private JButton rosterButton = new JButton("Roster");
    private JButton deleteWardeButton = new JButton("Delete Ward");
    private JButton calculateButton = new JButton("Calc");
    private JTextField calcSolution = new JTextField(10);
    private JList wardsList = new JList();
    private JScrollPane wardsPane = new JScrollPane();

    WardsWindowView(){

        // Sets up the view and adds the components
        // Without using GUI Form designer (BOSS!)

        JPanel listPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JLabel titleLabel = new JLabel("Wards");
        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24));

        this.setTitle("Nurse Rosters");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 430);
        this.setResizable(false);
        this.add("North", titleLabel);

        wardsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Ward 1", "Ward 2", "Ward 3", "Ward 4", "Ward 5","Ward 6","Ward 7","Ward 8","Ward 9",  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        wardsPane.setViewportView(wardsList);
        wardsPane.setPreferredSize(new Dimension(500, 370));

        listPanel.add(wardsPane);

        newWardButton.setPreferredSize(new Dimension(120,30));
        propertiesButton.setPreferredSize(new Dimension(120,30));
        rosterButton.setPreferredSize(new Dimension(120,30));
        deleteWardeButton.setPreferredSize(new Dimension(120,30));

        buttonsPanel.add(newWardButton);
        buttonsPanel.add(propertiesButton);
        buttonsPanel.add(rosterButton);
        buttonsPanel.add(deleteWardeButton);

        listPanel.setPreferredSize(new Dimension(500,370));
        buttonsPanel.setPreferredSize(new Dimension(130,370));

        this.add("West",listPanel);
        this.add("East", buttonsPanel);

        // End of setting up the components --------

    }

    public int getFirstNumber(){

        return Integer.parseInt(firstNumber.getText());

    }

    public int getSecondNumber(){

        return Integer.parseInt(secondNumber.getText());

    }

    public int getCalcSolution(){

        return Integer.parseInt(calcSolution.getText());

    }

    public void setCalcSolution(int solution){

        calcSolution.setText(Integer.toString(solution));

    }

    // If the calculateButton is clicked execute a method
    // in the Controller named actionPerformed

    void addCalculateListener(ActionListener listenForCalcButton){

        calculateButton.addActionListener(listenForCalcButton);

    }

    // Open a popup that contains the error message passed

    void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(this, errorMessage);

    }

}