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
    private static final int NUM_NURSES_TEST = 7;
    private static final int ROSTER_TYPE = Roster.ROSTER_7_DAY;

    public static final int DAY_FIRST_SEARCH = 1;
    public static final int NURSE_FIRST_SEARCH = 2;
    public static final int SEARCH_TYPE = DAY_FIRST_SEARCH;

    private static boolean testRun = false;

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

        /*
        r1.setNurseShiftPattern(0,Nurse.D);
        r1.setNurseShiftPattern(1,Nurse.DN);
        r1.setNurseShiftPattern(2,Nurse.DN);
        r1.setNurseShiftPattern(3,Nurse.DN);

        r1.setNurseShiftPattern(4,Nurse.D);
        r1.setNurseShiftPattern(5,Nurse.D);
        r1.setNurseShiftPattern(6,Nurse.D);
        r1.setNurseShiftPattern(7,Nurse.D);
        r1.setNurseShiftPattern(8,Nurse.D);
        r1.setNurseShiftPattern(9,Nurse.DN);
        r1.setNurseShiftPattern(10,Nurse.DN);
        r1.setNurseShiftPattern(11,Nurse.N);

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
        r1.setNurseGrade(10, Nurse.RN);
        r1.setNurseGrade(11, Nurse.RN);
        */

        r1.setNurseShiftPattern(0,Nurse.D);
        r1.setNurseShiftPattern(1,Nurse.N);
        r1.setNurseShiftPattern(2,Nurse.D);
        r1.setNurseShiftPattern(3,Nurse.N);

        r1.setNurseShiftPattern(4,Nurse.D);
        r1.setNurseShiftPattern(5,Nurse.D);
        r1.setNurseShiftPattern(6,Nurse.N);

        r1.setNurseGrade(0, Nurse.SRN);
        r1.setNurseGrade(1, Nurse.SRN);
        r1.setNurseGrade(2, Nurse.SRN);
        r1.setNurseGrade(3, Nurse.SRN);

        r1.setNurseGrade(4, Nurse.RN);
        r1.setNurseGrade(5, Nurse.RN);
        r1.setNurseGrade(6, Nurse.RN);

        testRun = true;

        Long start = System.currentTimeMillis();

        //Problem result = BacktrackCSP.rosterSolver(r1.initialState);
        Problem result = r1.run();

        Long finish = System.currentTimeMillis();

        // only prints to the console if the program was run from the main method rather than
        // As an object from another class
        if(testRun)
        {
            if(result != null)
            {
                result.printRoster();
            }
            else
            {
                System.out.println("\nSomething went wrong that I haven't made an error code for.");
            }

            for(String error : Problem.error)
            {
                System.out.print("\n" +error);
            }

            timeHandler(start, finish);
        }


    }

    public Problem run()
    {
        return BacktrackCSP.rosterSolver(initialState);
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

    private static void timeHandler(long start, long end)
    {
        long millis = end - start;
        long second = (millis / 100) % 60;
        long minute = (millis / (1000*60)) % 60;

        String time = String.format("%02d:%02d:%02d", minute, second, millis);

        System.out.println("Run time: " +time);
    }
}
