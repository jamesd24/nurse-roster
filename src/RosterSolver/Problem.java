package RosterSolver;

import java.util.ArrayList;

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

    private final int MAX_SHIFTS_7_DAY = 5;
    private final int MAX_SHIFTS_14_DAY = 10;

    // A string to output an error to for custom error handling
    public static ArrayList<String> error = new ArrayList<String>();

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
            maxShifts = MAX_SHIFTS_7_DAY;
        }
        else
        {
            maxShifts = MAX_SHIFTS_14_DAY;
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

        /**
         * If the shift isn't a day off, add one to the amount of days since the last day off
         */
        if(shift != Roster.SHIFT_OFF)
        {
            nurseList[nurse].setLastOff(nurseList[nurse].getLastOff()+1);
        }
        else
        {
            nurseList[nurse].setLastOff(0);
        }

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

    /**
     * Checks if all the places in the roster have been filled or not
     */
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

    /**
     * Returns the next empty shift in the roster
     */
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
        /**
         * If we have enough SRN nurses then we need to check that we have ones to cover off both day and night
         */
        if(numSRN > 3)
        {
            return checkDayNightSRN();
        }
        else
        {
            errorHandler("Not enough SRN nurses");
            return false;
        }

    }

    /**
     * Checks that both D and N shifts will have an available SRN
     */
    private boolean checkDayNightSRN()
    {
        int daySRN = 0;
        int nightSRN = 0;

        for(Nurse nurse : nurseList)
        {
            if(nurse.getGrade() == Nurse.SRN)
            {
                if(nurse.getShiftType() == Nurse.D || nurse.getShiftType() == Nurse.DN)
                {
                    daySRN++;
                }
                else if(nurse.getShiftType() == Nurse.N || nurse.getShiftType() == Nurse.DN)
                {
                    nightSRN++;
                }
            }
        }

        /**
         * Add errors if there aren't enough SRNs for a shift
         */
        if(!(daySRN >= getMinSRN()))
        {
            errorHandler("Not enough day SRNs.\nMinimum needed: " +getMinSRN() +"\nNumber currently rostered: " +daySRN);
        }
        if(!(nightSRN >= getMinSRN()))
        {
            errorHandler("Not enough night SRNs.\nMinimum needed: " +getMinSRN() +"\nNumber currently rostered: " +nightSRN);
        }

        return (daySRN >= getMinSRN()) && (nightSRN >= getMinSRN());
    }

    /**
     * Returns the minimum number of SRNs needed to fill the roster and cover off the day/night shifts
     * this takes into account the number of days in the roster and the maximum number of shifts a nurse can work per period
     */
    private int getMinSRN()
    {
        return (int) Math.ceil((float) roster.getPeriod() / maxShifts);
    }

    /**
     * Checks that the shift assignment for a nurse at a given day is allowable
     * returns true if it is, false if not.
     *
     * This is the main function that will be called by the CSP class to fill in the roster
     */
    public boolean checkValidAssignment(int nurse, int day, int shift)
    {
        if(canTakeShift(nurse, shift) && isRightShiftType(nurse, shift))
        {
            return true;
        }
        return false;
    }

    /**
     * Checks if a nurse can take another shift without having a day off
     */
    private boolean canTakeShift(int nurse, int shift)
    {
        /**
         * Can always take a shift off
         */
        if(shift == Roster.SHIFT_OFF)
        {
            return true;
        }

        /**
         * Otherwise check that they haven't worked too many days recently
         */
        if(roster.getPeriod() == Roster.ROSTER_7_DAY)
        {
            /**
             * First checks if the days since the last day off for the nurse is at the limit
             * If it isn't then it checks if the total number of shifts the nurse has done is
             * lower than the limit
             */
            if(nurseList[nurse].getLastOff() >= MAX_SHIFTS_7_DAY)
            {
                return false;
            }
            else
            {
                return countShifts(nurse) < MAX_SHIFTS_7_DAY;
            }
        }
        else if(roster.getPeriod() == Roster.ROSTER_14_DAY)
        {
            if(nurseList[nurse].getLastOff() >= MAX_SHIFTS_14_DAY)
            {
                return false;
            }
            else
            {
                return countShifts(nurse) < MAX_SHIFTS_14_DAY;
            }
        }

        errorHandler("Problem with roster assigning");
        return false;
    }

    /**
     * returns the number of shifts done by the given nurse during the current roster
     */
    private int countShifts(int nurse)
    {
        return roster.countNurseShifts(nurse);
    }

    /**
     * Checks if the shift type is the type a nurse can take
     */
    private boolean isRightShiftType(int nurse, int shift)
    {
        /**
         * All nurses can take a day off
         */
        if(shift == Roster.SHIFT_OFF)
        {
            return true;
        }
        /**
         * Check that the nurse's given shift pattern fits the shift assignment she is being given
         */
        if(nurseList[nurse].getShiftType() == Nurse.D)
        {
            return shift == Roster.SHIFT_DAY;
        }
        else if(nurseList[nurse].getShiftType() == Nurse.N)
        {
            return shift == Roster.SHIFT_NIGHT;
        }
        //TODO add in the night is followed by night or day off stipulation
        else if(nurseList[nurse].getShiftType() == Nurse.DN)
        {
            return (shift == Roster.SHIFT_DAY || shift == Roster.SHIFT_NIGHT) && dayNightNurseCheck(nurse, shift);
        }
        /**
         * If the nurse doesn't have an assigned shift pattern she is just assigned off for all
         */
        //TODO maybe look at extending this to give an error rather than just setting all shifts for that nurse to off
        // Will need to change the start of the function to accomodate that
        else
        {
            errorHandler("Nurse " + nurse + " does not have a shift assigned");
            return false;
        }
    }

    /**
     * Checks if a nurse with the shift type of DN can take a shift
     * If the last shift was a night shift then the next shift can only be either N or O
     */
    private boolean dayNightNurseCheck(int nurse, int shift)
    {
        if(nurseList[nurse].getLastShift() == Roster.SHIFT_NIGHT)
        {
            return shift == Roster.SHIFT_NIGHT || shift == Roster.SHIFT_OFF;
        }
        else
        {
            return true;
        }
    }

    /**
     * Prints the roster to the command line
     */
    public void printRoster()
    {
        roster.printRoster();
    }
    /**
     * Creates error strings, discards repeat errors
     */
    private void errorHandler(String in)
    {
        if(!error.contains(in))
        {
            error.add(in);
        }
    }
}
