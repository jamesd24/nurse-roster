package NursePP;

import Data.Nurse;
import WardPP.WardPPModel;


/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * Nurse PP Main.
 */
public class NursePPMain {
    public NursePPMain(WardPPModel m) {
        NursePPView theView = new NursePPView();
        WardPPModel theModel = m;
        NursePPController theController = new NursePPController(theView,theModel);
        theView.setVisible(true);
    }
    public NursePPMain(Nurse n, WardPPModel m){
        NursePPView theView = new NursePPView();
        WardPPModel theModel = m;
        NursePPController theController = new NursePPController(theView,theModel, n);
        theView.setVisible(true);
    }
}
