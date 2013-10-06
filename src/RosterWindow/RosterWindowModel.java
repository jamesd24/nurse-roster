package RosterWindow;

import Data.Nurse;
import Data.Ward;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterWindowModel {
    // Holds the values of the selected Ward to be used for Roster generation.
    private Ward ward;

    public RosterWindowModel(){}

    public RosterWindowModel(Ward ward){
        this.ward = ward;
    }
    //TODO RosterSolver entry point.
    public void generateRoster(){
        ArrayList<Nurse> nurses = ward.getListOfNurses();
         System.out.println("USED FOR TESTING PURPOSES!------------------------");
        System.out.println("Ward Name: "+ward.getWardName()+" Roster Days: "+ ward.getRoster() + " Number of Nurses: "+ward.getListOfNurses().size());
        System.out.println("    Nurses:");
        for (int i = 0;i<nurses.size();i++){
            Nurse n = nurses.get(i);
            System.out.print("       Nurse Name: ");
             System.out.println(n.getNurseName()+" Qualification: "+n.getQualification()+" Shift Pattern: "+n.getShiftPattern()+" Number of Shifts: "+n.getShifts());
        }
        System.out.println("--------------------------------------------------");

    }


}
