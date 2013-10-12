package RosterWindow;

import Data.Nurse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JPanel rosterPanel = new JPanel();
    private JTable rosterTable = new JTable();
    private JScrollPane rosterScroll = new JScrollPane();
    private String[] columnNames = {"Nurse Name","Shift Pattern","Grade","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    DefaultTableModel tableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    RosterWindowView(){

        // Sets up the view and adds the components
        // Without using GUI Form designer (BOSS!)

        JPanel listPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.setTitle("{Ward} - Roster");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(880, 680);
        this.setResizable(false);


        Object[][] data = {
                {"TestNurse1","DN","SRN","D","D","D","N","N",0,0},
                {"TestNurse2","DN","SRN","D","D","D","N","N",0,0},
                {"TestNurse3","DN","SRN","D","D","D","N","N",0,0},
                {"TestNurse4","DN","SRN","D","D","D","N","N",0,0},
                {"TestNurse5","DN","SRN","D","D","D","N","N",0,0},
                {"TestNurse6","DN","SRN","D","D","D","N","N",0,0},
                {"TestNurse7","DN","SRN","D","D","D","N","N",0,0},
        };
        for(int i = 0; i<columnNames.length; i++){
            tableModel.addColumn(columnNames[i]);
        }
        for(int i = 0; i<data.length; i++){
            tableModel.addRow(data[i]);
        }
        rosterTable.setModel(tableModel);
        rosterTable.setPreferredScrollableViewportSize(new Dimension(800,500));
        rosterTable.setFillsViewportHeight(true);
        rosterScroll.setViewportView(rosterTable);

        rosterPanel.setPreferredSize(new Dimension(810, 540));
        rosterPanel.add(rosterScroll);
        listPanel.add(rosterPanel);

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
    public void setRosterTable(ArrayList<ArrayList<String>> resultString, ArrayList<Nurse> nurseList) {
        //Remove Rows
        tableModel.setRowCount(0);
        //Setup new rows
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        for(int i = 0; i<nurseList.size(); i++){
            ArrayList<Object> row = new ArrayList<Object>();
            row.add(nurseList.get(i).getNurseName());
            row.add(nurseList.get(i).getShiftPattern());
            row.add(nurseList.get(i).getQualification());
            for(int j = 0; j<resultString.size();j++){
                row.add(resultString.get(i).get(j));
            }
            data.add(row);
        }
        //Save new data into tableModel
        for(int i = 0; i<data.size(); i++){
            tableModel.addRow(data.get(i).toArray());
        }
        //Reset JTable to show new table model
        rosterTable.setModel(tableModel);
    }
    public void toggleGenerateButton(){
        if(generateRosterButton.isEnabled()){
            generateRosterButton.setText("Loading Roster");
            generateRosterButton.setEnabled(false);
        }
        else{
            generateRosterButton.setText("Generate Roster");
            generateRosterButton.setEnabled(true);
        }

    }
}

