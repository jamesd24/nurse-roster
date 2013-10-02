package RosterSolver;
/**
 * Created with IntelliJ IDEA.
 * User: Phil
 * Date: 9/23/13
 * Time: 1:38 PM
 *
 * Defines the problem object for the scheduling
 * Including a roster with the shift types for each nurse on each day.
 * As well as a list of the nurses in the system
 */
public class Problem
{

    private Nurse[] nurseList;
    private Roster roster;
    private int maxShifts;
    private int nurses;
    private int days;

    public Problem(int nurses, int period)
    {
        // Setup the new roster object and a list of nurses to be used.
        roster = new Roster(nurses, period);
        nurseList = new Nurse[nurses];

        this.nurses = nurses;
        this.days = period;

        // Add nurses to the list
        for(int i = 0; i < nurses; i++)
        {
            nurseList[i] = new Nurse();
        }

        // Sets the max number of shifts the nurses can do, depending on the period being defined
        if(period == Roster.ROSTER_7_DAY)
        {
            maxShifts = 5;
        }
        else
        {
            maxShifts = 10;
        }
    }

    // Return the nurse for the given day
    public Nurse getNurse(int nurse)
    {
        return nurseList[nurse];
    }

    // Sets the day for the nurse to one of the shift types
    public void setNurseShift(int nurse, int day, int shift)
    {
        roster.setShift(nurse, day, shift);
    }

    // Sets the shift type of the nurse
    public void setNurseShiftType(int nurse, int shiftType)
    {
        nurseList[nurse].setShiftType(shiftType);
    }

    // Get the shift assigned to the nurse and day
    public int getShift(int nurse, int day)
    {
        return roster.getShift(nurse, day);
    }

    public void setNurseGrade(int nurse, int grade)
    {
        nurseList[nurse].setGrade(grade);
    }

    // Checks to see if all days have been filled in or not
    public boolean isComplete()
    {
        int check[] = getEmptyShift();
        if(check[0] == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Finds the next available empty shift on the roster
    public int[] getEmptyShift()
    {
        int[] emptyDay = new int[2];

        // Search for an empty shift
        for(int i = 0; i < nurses; i++)
        {
            for(int j = 0; j < days; j++)
            {
                if(roster.getShift(i,j) == Roster.NOT_SET)
                {
                    // If there is an empty shift then return the location
                    emptyDay[0] = i;
                    emptyDay[1] = j;
                    return emptyDay;
                }
            }
        }

        // If there isn't then return -1
        emptyDay[0] = -1;
        emptyDay[1] = -1;

        return emptyDay;
    }

    public boolean checkValidAssignment(int nurse, int day, int shift)
    {
        return true;
    }

    public void printRoster()
    {
        roster.printRoster();
    }

}
