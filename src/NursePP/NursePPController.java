package NursePP;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */

import Data.Nurse;
import WardPP.WardPPModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class NursePPController {

    private NursePPView theView;
    private WardPPModel theModel;
    private Boolean isProperties = false;
    private Nurse selectedNurse;

    public NursePPController(NursePPView theView, WardPPModel theModel, Nurse n) {
        setupController(theView,theModel);
        isProperties = true;
        selectedNurse = n;
        this.theView.setupPropertiesData(n);

    }
    public NursePPController(NursePPView theView, WardPPModel theModel) {
        setupController(theView,theModel);
        this.theView.setTitle("New Ward");
    }
    private void setupController(NursePPView theView, WardPPModel theModel){
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addCloseListener(new CloseListener());
        this.theView.addOkListener(new OkListener());
        this.theView.addApplyListener(new ApplyListener());
    }

    class OkListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //TODO Implement Error Handling and Constraints.
            if(isProperties == true){
                Nurse n = new Nurse(theView.nurseName.getText(),selectedNurse.getId(),theView.QualificationBox.getSelectedItem().toString(),Integer.parseInt(theView.shiftNum.getText()),theView.ShiftBox.getSelectedItem().toString());
                theModel.nurseList.set(selectedNurse.getId(), n);
            }
            else {
                Nurse n = new Nurse(theView.nurseName.getText(),theModel.generateNurseId(),theView.QualificationBox.getSelectedItem().toString(),Integer.parseInt(theView.shiftNum.getText()),theView.ShiftBox.getSelectedItem().toString());
                theModel.nurseList.add(n);
            }
            theView.dispose();
        }
    }
    class ApplyListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //TODO Implement Error Handling and Constraints.
            if(isProperties == true){
                Nurse n = new Nurse(theView.nurseName.getText(),selectedNurse.getId(),theView.QualificationBox.getSelectedItem().toString(),Integer.parseInt(theView.shiftNum.getText()),theView.ShiftBox.getSelectedItem().toString());
                theModel.nurseList.set(selectedNurse.getId(), n);
            }
            else {
                Nurse n = new Nurse(theView.nurseName.getText(),theModel.generateNurseId(),theView.QualificationBox.getSelectedItem().toString(),Integer.parseInt(theView.shiftNum.getText()),theView.ShiftBox.getSelectedItem().toString());
                theModel.nurseList.add(n);
                selectedNurse = n;
                isProperties = true;
                theView.setupPropertiesData(selectedNurse);
            }
        }
    }
    class CloseListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            theView.dispose();

        }
    }
}
