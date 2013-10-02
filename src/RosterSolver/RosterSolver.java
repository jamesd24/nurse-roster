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
    private static final int NUM_NURSES_TEST = 5;
    private static final int ROSTER_TYPE = Roster.ROSTER_14_DAY;
    private Problem initialState;

    public RosterSolver(int nurses, int period)
    {
        initialState = new Problem(nurses, period);
    }


    public static void main(String args[])
    {
        RosterSolver r1 = new RosterSolver(NUM_NURSES_TEST, ROSTER_TYPE);

        r1.setNurseShiftPattern(0,Roster.SHIFT_DAY);
        r1.setNurseShiftPattern(1, Roster.SHIFT_NIGHT);

        Problem result = BacktrackCSP.rosterSolver(r1.initialState);

        if(result != null)
        {
            result.printRoster();
        }
        else
        {
            System.out.println("Unable to complete");
        }
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
