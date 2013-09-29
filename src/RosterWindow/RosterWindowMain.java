package RosterWindow;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterWindowMain {
    public RosterWindowMain() {
        RosterWindowView theView = new RosterWindowView();
        RosterWindowModel theModel = new RosterWindowModel();
        RosterWindowController theController = new RosterWindowController(theView,theModel);
        theView.setVisible(true);
    }
}
