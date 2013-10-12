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

    private int[] minShiftDay;
    private int[] minShiftNight;

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

        // Create the arrays that hold the minShift amounts for day and night
        minShiftDay = new int[period];
        minShiftNight = new int[period];

        setupShiftArrays();
    }

    /**
     * Get and set for the minShift arrays
     */
    public void setMinShiftDay(int day, int min)
    {
        minShiftDay[day] = min;
    }

    public int getMinShiftDay(int day)
    {
        return minShiftDay[day];
    }

    public void setMinShiftNight(int day, int min)
    {
        minShiftNight[day] = min;
    }

    public int getMinShiftsNight(int day)
    {
        return minShiftNight[day];
    }

    /**
     * Return the nurse for the given day
     */
    public Nurse getNurse(int nurse)
    {
        return nurseList[nurse];
    }

    /**
     * Sets the shift of a given nurse on a given day to the given shift type
     */
    public void setNurseShift(int nurse, int day, int shift)
    {
        roster.setShift(nurse, day, shift);

        /**
         * If the shift isn't a day off, add one to the amount of days since the last day off
         */
        if(shift == Roster.SHIFT_DAY || shift == Roster.SHIFT_NIGHT)
        {
            nurseList[nurse].setLastOff(nurseList[nurse].getLastOff()+1);
        }
        else if(shift == Roster.NOT_SET)
        {
            nurseList[nurse].setLastOff(nurseList[nurse].getLastOff()-1);
        }
        else if(shift == Roster.SHIFT_OFF)
        {
            nurseList[nurse].setLastOff(0);
        }
    }

    /**
     * Sets the shift type of the given nurse to the given type
     */
    public void setNurseShiftType(int nurse, int shiftType)
    {
        nurseList[nurse].setShiftType(shiftType);
    }

    /**
     * Return the shift type of the nurse on a given day
     */
    public int getShift(int nurse, int day)
    {
        return roster.getShift(nurse, day);
    }

    /**
     * Sets the grade of the given nurse
     */
    public void setNurseGrade(int nurse, int grade)
    {
        nurseList[nurse].setGrade(grade);
    }

    /**
     * Returns the number of nurses in the roster
     */
    public int getNumNurses()
    {
        return nurses;
    }

    /**
     * Checks if all the places in the roster have been filled or not
     */
    public boolean isComplete()
    {
        // Checks if there is an empty space in the roster
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
        if(RosterSolver.SEARCH_TYPE == RosterSolver.DAY_FIRST_SEARCH)
        {
            return dayFirstSearch();
        }
        else if(RosterSolver.SEARCH_TYPE == RosterSolver.NURSE_FIRST_SEARCH)
        {
            return nurseFirstSearch();
        }
        else
        {
            return new int[] {-1,-1};
        }
    }

    // Day first
    public int[] dayFirstSearch()
    {
        int[] emptyDay = new int[2];

        // Search for an empty shift
        for(int i = 0; i < days; i++)
        {
            for(int j = 0; j < nurses; j++)
            {
                if(roster.getShift(j,i) == Roster.NOT_SET)
                {
                    // If there is an empty shift then return the location
                    emptyDay[0] = j;
                    emptyDay[1] = i;
                    return emptyDay;
                }
            }
        }

        // If there isn't then return -1
        emptyDay[0] = -1;
        emptyDay[1] = -1;

        return emptyDay;
    }

    // Nurse First
    public int[] nurseFirstSearch()
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
        // Setup the shift minimum arrays for later on, only done here as this code is run before any looping is done
        setMinShifts();

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
                if(nurse.getShiftType() == Nurse.D)
                {
                    daySRN++;
                }
                else if(nurse.getShiftType() == Nurse.N)
                {
                    nightSRN++;
                }
                else if(nurse.getShiftType() == Nurse.DN)
                {
                    daySRN++;
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
        // First check that the nurse in question can take the assigned shift type
        if(canTakeShift(nurse, shift) && isRightShiftType(nurse, shift))
        {
            return true;
        }
        return false;
    }

    /**
     * Checks that all the conditions on the days are fulfilled
     */
    public boolean checkDay(int day)
    {
        return checkSrnShift(day) && checkMinShifts(day);
    }

    /**
     * Checks the schedule to make sure the given day has a SRN working both the day and the night shift
     */
    private boolean checkSrnShift(int day)
    {
        boolean daySRN = false;
        boolean nightSRN = false;

        for(int i = 0; i < nurses; i++)
        {
            if(getNurse(i).getGrade() == Nurse.SRN)
            {
                if(roster.getShift(i, day) == Roster.SHIFT_DAY)
                {
                    daySRN = true;
                }
                else if(roster.getShift(i, day) == Roster.SHIFT_NIGHT)
                {
                    nightSRN = true;
                }
            }
        }

        return daySRN && nightSRN;
    }

    /**
     * Checks that the given day has enough people working each shift
     * defined in the minShift array
     */
    private boolean checkMinShifts(int day)
    {
        int dayCount = 0;
        int nightCount = 0;

        for(int i = 0; i < nurses; i++)
        {
            if(getShift(i, day) == Roster.SHIFT_DAY)
            {
                dayCount++;
            }
            else if(getShift(i, day) == Roster.SHIFT_NIGHT)
            {
                nightCount++;
            }
        }

        return dayCount >= minShiftDay[day] && nightCount >= minShiftNight[day];
    }

    /**
     * Calculates the remaining minimum shift amounts for each of the days using the provided data as a baseline
     */
    //TODO This might need tweaking a bit
    //TODO still needs to have the weekend = less staffing set
    private void setMinShifts()
    {
        /**
         * Generate numbers for the rest of the days based on:
         *  Half the roster to be working through the day
         *      1/4 roster to be working night and 1/4 day off
         */
        int dayShift = nurses / 2;
        int nightShift = dayShift / 2;

        /**
         * Fill in the rest of the days from tne values derived above
         */
        //TODO this code needs to be tidied up a bit, it could be made shorter
        for(int i = 0; i < days; i++)
        {
            if(minShiftDay[i] == -1)
            {
                if(minShiftNight[i] == -1)
                {
                    if((i % 6 == 0) || (i % 7 == 0 ))
                    {
                        minShiftDay[i] = dayShift -1;
                        minShiftNight[i] = nightShift -1;
                    }

                    minShiftDay[i] = dayShift;
                    minShiftNight[i] = nightShift;
                }
                else
                {
                    minShiftDay[i] = minShiftNight[i]*2;
                }
            }

            if(minShiftNight[i] == -1)
            {
                if(minShiftDay[i] == -1)
                {
                    if((days % 6 == 0) || (days % 7 ==- 0 ))
                    {
                        minShiftDay[i] = dayShift -1;
                        minShiftNight[i] = nightShift -1;
                    }

                    minShiftDay[i] = dayShift;
                    minShiftNight[i] = nightShift;
                }
                else
                {
                    minShiftNight[i] = (int) Math.ceil(minShiftDay[i] / 2.0);
                }
            }
        }
    }

    /**
     * Sets the minShiftDay and minShiftNight arrays to -1
     */
    private void setupShiftArrays()
    {
        for(int i = 0; i < days; i++)
        {
            minShiftDay[i] = -1;
            minShiftNight[i] = -1;
        }
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
    public void errorHandler(String in)
    {
        if(!error.contains(in))
        {
            error.add(in);
        }
    }
    /**
     * Returns the completed roster
     */
    public ArrayList<ArrayList<String>> getCompletedRoster(){
        ArrayList<ArrayList<String>> rosterArray = roster.getCompletedRoster();
        return rosterArray;
    }
}
