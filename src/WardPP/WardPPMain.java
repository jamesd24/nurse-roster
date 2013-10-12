package WardPP;

import Data.Ward;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * The main for a WardPP. A blank constructor is used for a new ward and a ward is passed when a property page is required.
 */
public class WardPPMain {
    public WardPPMain() {
        WardPPView theView = new WardPPView();
        WardPPModel theModel = new WardPPModel();
        WardPPController theController = new WardPPController(theView,theModel);
        theView.setVisible(true);
    }
    public WardPPMain(Ward w){
        WardPPView theView = new WardPPView();
        WardPPModel theModel = new WardPPModel(w);
        WardPPController theController = new WardPPController(theView,theModel, w);
        theView.setVisible(true);
    }
}
