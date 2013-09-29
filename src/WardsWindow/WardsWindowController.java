package WardsWindow;
/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
import RosterWindow.RosterWindowMain;

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

        // Tell the View that when ever the calculate button
        // is clicked to execute the actionPerformed method
        // in the CalculateListener inner class

        this.theView.addCalculateListener(new CalculateListener());
        this.theView.addRosterListener(new RosterListener());
        this.theView.addPropertiesListener(new PropertiesListener());
        this.theView.addDeleteWardListener(new DeleteWardListener());
        this.theView.addNewWardListener(new NewWardListener());
    }

    class CalculateListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            int firstNumber, secondNumber = 0;

            // Surround interactions with the view with
            // a try block in case numbers weren't
            // properly entered

            try{

                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();

                theModel.addTwoNumbers(firstNumber, secondNumber);

                theView.setCalcSolution(theModel.getCalculationValue());

            }

            catch(NumberFormatException ex){

                System.out.println(ex);

                theView.displayErrorMessage("You Need to Enter 2 Integers");

            }

        }

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

            theView.displayErrorMessage("To Be Implemented");

        }
    }
    class DeleteWardListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            theView.displayErrorMessage("To Be Implemented");

        }
    }

}