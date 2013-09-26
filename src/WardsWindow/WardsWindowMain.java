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
        //create Model and View
        WardsWindowModel myModel 	= new WardsWindowModel();
        WardsWindowView myView 	= new WardsWindowView();
        //model acting on view
        myModel.addObserver(myView);
        //addController setup Model and View
        WardsWindowController myController = new WardsWindowController();
        myController.addModel(myModel);
        myController.addView(myView);
        myController.initModel(10);
        myView.addController(myController);
    }

}
