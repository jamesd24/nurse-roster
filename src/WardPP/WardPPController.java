package WardPP;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */

import NursePP.NursePPMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class WardPPController {

    private WardPPView theView;
    private WardPPModel theModel;

    public WardPPController(WardPPView theView, WardPPModel theModel) {
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

            theView.displayErrorMessage("To Be Implemented");

        }
    }
    class ApplyListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            theView.displayErrorMessage("To Be Implemented");

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
