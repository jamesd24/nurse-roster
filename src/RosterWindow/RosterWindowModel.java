package RosterWindow;

import Data.Nurse;
import Data.Ward;
import RosterSolver.*;
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
    public Ward ward;
    private ArrayList<ArrayList<String>> resultString;

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

        RosterSolver r = new RosterSolver(ward.getListOfNurses().size(), ward.getRoster());
        ArrayList<Nurse> nurseList = ward.getListOfNurses();
        int SP = 0;
        int Q = 0;
        for(int i = 0; i<nurseList.size(); i++){
            if(nurseList.get(i).getShiftPattern().equals("DN")){
                SP = 6;
            } if(nurseList.get(i).getShiftPattern().equals("N")){
                SP = 5;
            }
            if(nurseList.get(i).getShiftPattern().equals("D")){
                SP = 4;
            }

            if(nurseList.get(i).getQualification().equals("SRN")){
                Q = 1;
            }
            if(nurseList.get(i).getQualification().equals("RN")){
                Q = 2;
            }

            r.setNurseShiftPattern(i, SP);
            r.setNurseGrade(i, Q);
        }
       Problem result =  r.run();
       for(String error : Problem.error)
       {
           System.out.print("\n" +error);
       }
        resultString = result.getCompletedRoster();
        for(int i = 0; i<resultString.size(); i++){
                System.out.println(resultString.get(i));
        }

        //result.printRoster();
    }
    public ArrayList<ArrayList<String>> getResultString(){
        return resultString;
    }
}
