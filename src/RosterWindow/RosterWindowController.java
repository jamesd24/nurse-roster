package RosterWindow;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:13 PM
 * Roster Window Controller
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
        this.theView.setTitle(theModel.ward.getWardName() + " - Roster");
        this.theView.addGenerateListener(new GenerateListener());
        this.theView.addPrintListener(new PrintListener());
        this.theView.addCloseListener(new CloseListener());
    }

    /**
     * GenerateLister for generate roster button. Created a new thread so the RosterSolver can run in the background.
     */
    class GenerateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    theView.toggleGenerateButton();
                    theModel.generateRoster();
                    theView.setRosterTable(theModel.getResultString(), theModel.ward.getListOfNurses());
                    theView.toggleGenerateButton();
                }
            };
            new Thread(r).start();
        }
    }

    /**
     * Is meant to be able to print the roster table to paper.. but this is not required.
     */
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
