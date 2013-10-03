package WardsWindow;
/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
import Data.Ward;
import RosterWindow.RosterWindowMain;
import WardPP.WardPPMain;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

// The Controller coordinates interactions
// between the View and Model

public class WardsWindowController {

    private WardsWindowView theView;
    private WardsWindowModel theModel;

    public WardsWindowController(WardsWindowView theView, WardsWindowModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        refreshWardsList();
        this.theView.addRosterListener(new RosterListener());
        this.theView.addPropertiesListener(new PropertiesListener());
        this.theView.addDeleteWardListener(new DeleteWardListener());
        this.theView.addNewWardListener(new NewWardListener());
        this.theView.addFocusViewListener(new FocusListener());
    }

    private void refreshWardsList() {
        ArrayList<String> wardList = theModel.getWardList();
        DefaultListModel m = new DefaultListModel();
        for(int i = 0; i < wardList.size() ; ++i){
            m.addElement(wardList.get(i));
        }
        theView.wardsList.setModel(m);
    }

    class RosterListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            RosterWindowMain roster = new RosterWindowMain();

        }
    }
    class NewWardListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            WardPPMain pp = new WardPPMain();

        }
    }
    class PropertiesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            WardPPMain pp = new WardPPMain(theModel.getWardAt(theView.wardsList.getSelectedIndex()));

        }
    }
    class DeleteWardListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(theView.wardsList.getModel().getSize() == 1){
                theView.displayErrorMessage("Error: Must contain at least one ward");
            }
            else{
                theModel.deleteWard(theView.wardsList.getSelectedIndex());
                refreshWardsList();
            }
        }
    }
    class FocusListener extends WindowAdapter {
        public void windowGainedFocus(WindowEvent e){
            refreshWardsList();
        }
    }

}