package WardsWindow;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class WardsWindowMain {
    public WardsWindowMain() {
        WardsWindowView theView = new WardsWindowView();
        WardsWindowModel theModel = new WardsWindowModel();
        WardsWindowController theController = new WardsWindowController(theView,theModel);
        theView.setVisible(true);
    }
}
