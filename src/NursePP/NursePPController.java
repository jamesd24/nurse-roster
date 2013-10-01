package NursePP;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class NursePPController {

    private NursePPView theView;
    private NursePPModel theModel;

    public NursePPController(NursePPView theView, NursePPModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        // Tell the View that when ever a button
        // is clicked to execute the actionPerformed method

        this.theView.addOkListener(new OkListener());
        this.theView.addCloseListener(new CloseListener());
        this.theView.addApplyListener(new ApplyListener());

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
}
