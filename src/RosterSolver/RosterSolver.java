package RosterSolver;
/**
 * Created with IntelliJ IDEA.
 * User: Phil
 * Date: 9/23/13
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class RosterSolver
{
    private static final int NUM_NURSES_TEST = 10;
    private static final int ROSTER_TYPE = Roster.ROSTER_7_DAY;

    private Problem initialState;

    public RosterSolver(int nurses, int period)
    {
        initialState = new Problem(nurses, period);
    }


    /**
     * USED ONLY FOR TESTING PURPOSES
     */
    public static void main(String args[])
    {
        RosterSolver r1 = new RosterSolver(NUM_NURSES_TEST, ROSTER_TYPE);

        r1.setNurseShiftPattern(0,Nurse.D);
        r1.setNurseShiftPattern(1,Nurse.N);
        r1.setNurseShiftPattern(2,Nurse.D);
        r1.setNurseShiftPattern(3,Nurse.N);
        r1.setNurseShiftPattern(4,Nurse.D);
        r1.setNurseShiftPattern(5,Nurse.N);
        r1.setNurseShiftPattern(6,Nurse.N);
        r1.setNurseShiftPattern(7,Nurse.D);
        r1.setNurseShiftPattern(8,Nurse.D);
        r1.setNurseShiftPattern(9,Nurse.N);

        r1.setNurseGrade(0, Nurse.SRN);
        r1.setNurseGrade(1, Nurse.SRN);
        r1.setNurseGrade(2, Nurse.SRN);
        r1.setNurseGrade(3, Nurse.SRN);
        r1.setNurseGrade(4, Nurse.RN);
        r1.setNurseGrade(5, Nurse.RN);
        r1.setNurseGrade(6, Nurse.RN);
        r1.setNurseGrade(7, Nurse.RN);
        r1.setNurseGrade(8, Nurse.RN);
        r1.setNurseGrade(9, Nurse.RN);

        Long start = System.currentTimeMillis();

        Problem result = BacktrackCSP.rosterSolver(r1.initialState);

        Long finish = System.currentTimeMillis();

        if(result != null)
        {
            result.printRoster();
        }

        for(String error : Problem.error)
        {
            System.out.print("\n" +error);
        }

        System.out.println("\nRun time: " +((finish-start)/1000.00) +" seconds.");

    }

    // Gives a certain nurse a certain shift type on a certain day
    public void setShift(int nurse, int day, int shiftType)
    {
        initialState.setNurseShift(nurse, day, shiftType);
    }

    // Sets a nurse to either day, night or day/night
    public void setNurseShiftPattern(int nurse, int pattern)
    {
        initialState.setNurseShiftType(nurse, pattern);
    }

    public void setNurseGrade(int nurse, int grade)
    {
        initialState.setNurseGrade(nurse, grade);
    }
}
