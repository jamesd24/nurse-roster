package RosterWindow;

import Data.Ward;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * Roster Window Main.
 */
public class RosterWindowMain {
    public RosterWindowMain() {
        RosterWindowView theView = new RosterWindowView();
        RosterWindowModel theModel = new RosterWindowModel();
        RosterWindowController theController = new RosterWindowController(theView,theModel);
        theView.setVisible(true);
    }
    public RosterWindowMain(Ward w) {
        RosterWindowView theView = new RosterWindowView();
        RosterWindowModel theModel = new RosterWindowModel(w);
        RosterWindowController theController = new RosterWindowController(theView,theModel);
        theView.setVisible(true);
    }
}
