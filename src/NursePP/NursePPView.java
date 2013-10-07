package NursePP;

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
public class NursePPView extends JFrame
{
    private String[] qstring = {"SRN","RN"};
    private String[] pstring = {"DN","D","N"};
    private JButton closeButton = new JButton("Close");
    private JButton okButton = new JButton("OK");
    private JButton applyButton = new JButton("Apply");
    public JComboBox ShiftBox = new JComboBox(pstring);
    public JComboBox QualificationBox = new JComboBox(qstring);
    public JTextField nurseName = new JTextField(10);
    public JTextField shiftNum = new JTextField(2);


    NursePPView(){

        // Sets up the view and adds the components
        // Without using GUI Form designer (BOSS!)

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.setTitle("{Nurse Name} - Properties");
        this.setSize(280, 230);
        this.setResizable(false);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 16));
        JLabel sLabel = new JLabel("Shifts: ");
        sLabel.setFont(new java.awt.Font("Tahoma", 0, 16));
        JLabel spLabel = new JLabel("Shift Pattern: ");
        spLabel.setFont(new java.awt.Font("Tahoma", 0, 16));
        spLabel.setBorder(BorderFactory.createEmptyBorder(0,0,1,5));
        JLabel qLabel = new JLabel("Qualification: ");
        qLabel.setFont(new java.awt.Font("Tahoma", 0, 16));
        qLabel.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));

        closeButton.setPreferredSize(new Dimension(70,25));
        applyButton.setPreferredSize(new Dimension(70,25));
        okButton.setPreferredSize(new Dimension(70,25));

        labelPanel.add(nameLabel);
        labelPanel.add(qLabel);
        labelPanel.add(spLabel);
        labelPanel.add(sLabel);

        QualificationBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        ShiftBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));

        dataPanel.add(nurseName);
        dataPanel.add(QualificationBox);
        dataPanel.add(ShiftBox);
        dataPanel.add(shiftNum);


        buttonsPanel.add(closeButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(applyButton);

        dataPanel.setPreferredSize(new Dimension(170,160));
        labelPanel.setPreferredSize(new Dimension(100,160));
        buttonsPanel.setPreferredSize(new Dimension(130,35));

        this.add("East", dataPanel);
        this.add("West", labelPanel);
        this.add("South", buttonsPanel);

        // End of setting up the components --------

    }

    void addOkListener(ActionListener l){
        okButton.addActionListener(l);
    }
    void addApplyListener(ActionListener l){
        applyButton.addActionListener(l);
    }
    void addCloseListener(ActionListener l){
        closeButton.addActionListener(l);
    }

    // Open a popup that contains the error message passed

    void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(this, errorMessage);

    }

}

