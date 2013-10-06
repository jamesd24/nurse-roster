package RosterWindow;

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

public class RosterWindowController {

    private RosterWindowView theView;
    private RosterWindowModel theModel;

    public RosterWindowController(RosterWindowView theView, RosterWindowModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addGenerateListener(new GenerateListener());
        this.theView.addPrintListener(new PrintListener());
        this.theView.addCloseListener(new CloseListener());
    }

    class GenerateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            theModel.generateRoster();

        }
    }
    class PrintListener implements ActionListener{
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
