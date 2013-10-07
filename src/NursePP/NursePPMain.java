package NursePP;

import WardPP.WardPPModel;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class NursePPMain {
    public NursePPMain(WardPPModel m) {
        NursePPView theView = new NursePPView();
        WardPPModel theModel = m;
        NursePPController theController = new NursePPController(theView,theModel);
        theView.setVisible(true);
    }
}
