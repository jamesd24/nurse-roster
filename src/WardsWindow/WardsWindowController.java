package WardsWindow;
/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
import RosterWindow.RosterWindowMain;
import WardPP.WardPPMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class WardsWindowController {

    private WardsWindowView theView;
    private WardsWindowModel theModel;

    public WardsWindowController(WardsWindowView theView, WardsWindowModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addRosterListener(new RosterListener());
        this.theView.addPropertiesListener(new PropertiesListener());
        this.theView.addDeleteWardListener(new DeleteWardListener());
        this.theView.addNewWardListener(new NewWardListener());
    }

    class RosterListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            RosterWindowMain roster = new RosterWindowMain();

        }
    }
    class NewWardListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            theView.displayErrorMessage("To Be Implemented");

        }
    }
    class PropertiesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            WardPPMain pp = new WardPPMain();

        }
    }
    class DeleteWardListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            theView.displayErrorMessage("To Be Implemented");

        }
    }

}