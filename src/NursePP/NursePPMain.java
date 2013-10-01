package NursePP;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class NursePPMain {
    public NursePPMain() {
        NursePPView theView = new NursePPView();
        NursePPModel theModel = new NursePPModel();
        NursePPController theController = new NursePPController(theView,theModel);
        theView.setVisible(true);
    }
}
