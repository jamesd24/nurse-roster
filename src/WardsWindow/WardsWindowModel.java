package WardsWindow;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
// The Model performs all the calculations needed
// and that is it. It doesn't know the View
// exists

public class WardsWindowModel {

    // Holds the value of the sum of the numbers
    // entered in the view

    private int calculationValue;

    public void addTwoNumbers(int firstNumber, int secondNumber){

        calculationValue = firstNumber + secondNumber;

    }

    public int getCalculationValue(){

        return calculationValue;

    }
}
