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

    // A string to output an error to for custom error handling
    public String error = null;

    /**
     * Constructor that takes a number of nurses and a number of days
     */
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
        nurseList[nurse].setLastOff(nurseList[nurse].getLastOff()+1);
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

    public void printRoster()
    {
        roster.printRoster();
    }

    /**
     * Used to check the setup of the roster doesn't contain any disallowed
     * settings, such as not enough SRN nurses
     */
    public boolean checkInitialState()
    {
        int numSRN = 0;

        for(Nurse nurse : nurseList)
        {
            if(nurse.getGrade() == Nurse.SRN)
            {
                numSRN++;
            }
        }
        // At least 4 of the nurses in the roster should be SRN (This can be done earlier to reduce wasted reuse)
        if(numSRN > 3)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Checks that the shift assignment for a nurse at a given day is allowable
     * returns true if it is, false if not.
     *
     * This is the main function that will be called by the CSP class to fill in the roster
     */
    public boolean checkValidAssignment(int nurse, int day, int shift)
    {
        if(canTakeShift(nurse))
        {
            return true;
        }
        // Checks needed:
            // If 7 day shift, max 5 shifts, 14 day shift is 10
            // Nurses on shift type D can only work day, N can only work night, DN can work both
                // If the nurse is DN, then N can only be followed by N or O
            // Both D and N must have a SRN working every day
        return false;
    }

    /**
     * Checks if a nurse can take another shift without having a day off
     */
    private boolean canTakeShift(int nurse)
    {
        if(roster.getPeriod() == Roster.ROSTER_7_DAY)
        {
            if(nurseList[nurse].getLastOff() >= 5)
            {
                return false;
            }
        }
        else if(roster.getPeriod() == Roster.ROSTER_14_DAY)
        {
            if(nurseList[nurse].getLastOff() >= 10)
            {
                return false;
            }
        }

        return true;
    }

}
