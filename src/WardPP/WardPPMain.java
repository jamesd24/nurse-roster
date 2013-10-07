package WardPP;

import Data.Ward;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
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
