package WardPP;

import Data.Ward;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowFocusListener;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * WardPPView used to setup the gui of the WardPP.
 */
public class WardPPView extends JFrame
{
    private String[] lstring = {"Weekly","Fortnightly"};
    public JTextField wardName = new JTextField();
    private JButton closeButton = new JButton("Close");
    private JButton okButton = new JButton("OK");
    private JButton applyButton = new JButton("Apply");
    private JButton addNurseButton = new JButton("Add Nurse");
    private JButton propertiesButton = new JButton("Properties");
    private JButton deleteNurseButton = new JButton("Delete Nurse");
    public JList nurseList = new JList();
    private JScrollPane nursePane = new JScrollPane();
    public JComboBox lengthBox = new JComboBox(lstring);

    WardPPView(){

        // Sets up the view and adds the components

        JPanel listPanel = new JPanel();
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel optionButtonsPanel = new JPanel();

        this.setTitle("{Ward} - Properties");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 450);
        this.setResizable(false);

        JLabel nameLabel = new JLabel("Ward: ");
        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 24));
        JLabel rosterLabel = new JLabel("Roster Length: ");
        JLabel nurseLabel = new JLabel("Nurses");
        JLabel blank = new JLabel("                               ");

        nurseList.setModel(new AbstractListModel() {
            String[] nstrings = { "List Of Nurses|TODO"  };
            public int getSize() { return nstrings.length; }
            public Object getElementAt(int i) { return nstrings[i]; }
        });
        nursePane.setViewportView(nurseList);
        nursePane.setPreferredSize(new Dimension(230, 210));

        listPanel.add(nurseLabel);
        listPanel.add(nursePane);

        closeButton.setPreferredSize(new Dimension(100,25));
        applyButton.setPreferredSize(new Dimension(100,25));
        okButton.setPreferredSize(new Dimension(100,25));
        addNurseButton.setPreferredSize(new Dimension(120,30));
        propertiesButton.setPreferredSize(new Dimension(120,30));
        deleteNurseButton.setPreferredSize(new Dimension(120,30));

        wardName.setPreferredSize(new Dimension(300,20));

        topPanel.add(nameLabel);
        topPanel.add(wardName);
        topPanel.add(rosterLabel);
        topPanel.add(lengthBox);

        optionButtonsPanel.add(blank);
        optionButtonsPanel.add(addNurseButton);
        optionButtonsPanel.add(propertiesButton);
        optionButtonsPanel.add(deleteNurseButton);

        buttonsPanel.add(closeButton);
        buttonsPanel.add(okButton);
        buttonsPanel.add(applyButton);

        topPanel.setPreferredSize(new Dimension(400,90));
        listPanel.setPreferredSize(new Dimension(250,210));
        buttonsPanel.setPreferredSize(new Dimension(130,70));
        optionButtonsPanel.setPreferredSize(new Dimension(150,210));

        this.add("North", topPanel);
        this.add("West",listPanel);
        this.add("East", optionButtonsPanel);
        this.add("South", buttonsPanel);

        // End of setting up the components --------

    }

    /**
     * Add listeners to all buttons and window focus. So the controller knows when an event has occurred.
     */
    void addOkListener(ActionListener l){
        okButton.addActionListener(l);
    }
    void addApplyListener(ActionListener l){
        applyButton.addActionListener(l);
    }
    void addCloseListener(ActionListener l){
        closeButton.addActionListener(l);
    }
    void addNurseListener(ActionListener l){
        addNurseButton.addActionListener(l);
    }
    void addPropertiesListener(ActionListener l){
        propertiesButton.addActionListener(l);
    }
    void addDeleteListener(ActionListener l){
        deleteNurseButton.addActionListener(l);
    }
    void addFocusViewListener(WindowFocusListener l){
        this.addWindowFocusListener(l);
    }

    /**
    *  Open a popup that contains the error message passed.
    */
    void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(this, errorMessage);

    }

    /**
     * Used to set up the existing data when a property page is required.
     * @param w - The ward passed to the view so information can be set.
     */
    public void setupPropertiesData(Ward w){
        this.setTitle(w.getWardName()+ " - Properties");
        wardName.setText(w.getWardName());
        if(w.getRoster() == 7) lengthBox.setSelectedIndex(0);
        else lengthBox.setSelectedIndex(1);
    }

}

