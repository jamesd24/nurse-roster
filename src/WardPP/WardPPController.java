package WardPP;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */

//TODO Figure out how to save the properties of the ward without creating a new ward.
//TODO Make sure pressing the ok button after the apply button doesn't create 2 wards.


import Data.Ward;
import NursePP.NursePPMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class WardPPController {

    private WardPPView theView;
    private WardPPModel theModel;

    public WardPPController(WardPPView theView, WardPPModel theModel, Ward w) {
        setupController(theView,theModel);
        this.theView.setupPropertiesData(w);
    }
    public WardPPController(WardPPView theView, WardPPModel theModel) {
        setupController(theView,theModel);
        this.theView.setTitle("New Ward");
    }
    private void setupController(WardPPView theView, WardPPModel theModel){
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addOkListener(new OkListener());
        this.theView.addNurseListener(new AddNurseListener());
        this.theView.addCloseListener(new CloseListener());
        this.theView.addApplyListener(new ApplyListener());
        this.theView.addPropertiesListener(new PropertiesListener());
        this.theView.addDeleteListener(new DeleteNurseListener());
    }

    class OkListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if((theView.wardName.getText().trim() == "")){
                theView.displayErrorMessage("Error: Must specify ward name");
            }
            else{
                int roster = theView.lengthBox.getSelectedIndex();
                if(roster==0) roster = 7;
                if(roster==1) roster = 14;
                theModel.newWardDataToXML(theView.wardName.getText(),roster);
                theView.dispose();
            }
        }
    }
    class ApplyListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if((theView.wardName.getText().trim() == "")){
                theView.displayErrorMessage("Error: Must specify ward name");
            }
            else{
                int roster = theView.lengthBox.getSelectedIndex();
                if(roster==0) roster = 7;
                if(roster==1) roster = 14;
                theModel.newWardDataToXML(theView.wardName.getText(),roster);
            }
        }
    }
    class CloseListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            theView.dispose();
        }
    }
    class AddNurseListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            theView.displayErrorMessage("To Be Implemented");

        }
    }
    class DeleteNurseListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            theView.displayErrorMessage("To Be Implemented");
        }
    }
    class PropertiesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            NursePPMain nurse = new NursePPMain();

        }
    }
}
