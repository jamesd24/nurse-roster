package RosterSolver;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Phil
 * Date: 9/23/13
 * Time: 11:08 AM
 *
 * Defines the roster for the nurses
 */
public class Roster
{
    // Shift types for days of the week
    public static final int NOT_SET = -1;
    public static final int SHIFT_OFF = 0;
    public static final int SHIFT_DAY = 1;
    public static final int SHIFT_NIGHT = 2;

    // Roster lengths
    public static final int ROSTER_7_DAY = 7;
    public static final int ROSTER_14_DAY = 14;

    private int[][] roster;
    private int period, nurses;

    /**
     * Creates a new Roster with a given number of nurses for a given period
     * Use the enums to set the length
     */
    public Roster(int nurses, int period)
    {
        this.period = period;
        this.nurses = nurses;

        // Set the roster to the correct size
        roster = new int[this.nurses][this.period];

        // Set up the roster with dummy "blank" data
        for(int i = 0; i < nurses; i++)
        {
            for(int j = 0; j < period; j++)
            {
                roster[i][j] = NOT_SET;
            }
        }
    }

    /**
     * Sets the shift of a given nurse for a day
     */
    public void setShift(int nurse, int day, int type)
    {
        roster[nurse][day] = type;
    }

    /**
     * returns the shift type for a nurse at a given day
     */
    public int getShift(int nurse, int day)
    {
        return roster[nurse][day];
    }

    /**
     * Returns the roster period
     */
    public int getPeriod()
    {
        return period;
    }

    /**
     * Counts the number of shifts the given nurse has done
     */
    public int countNurseShifts(int nurse)
    {
        int count = 0;

        for(int i = 0; i < period; i++)
        {
            if(roster[nurse][i] == SHIFT_DAY || roster[nurse][i] == SHIFT_NIGHT)
            {
                count++;
            }
        }

        return count;
    }

    /**
     * Prints the roster out in rows of the form
     * Nurse 1: shift shift shift shift shift
     * Nurse 2: shift shift shift shift shift
     * etc
     */
    public void printRoster()
    {
        for(int i = 0; i < nurses; i++)
        {
            for(int j = 0; j < period; j++)
            {
                if(j == 0)
                {
                    System.out.print("Nurse " +(i+1) + ":\t");
                }
                if(roster[i][j] == SHIFT_DAY)
                {
                    System.out.print(" D ");
                }
                else if(roster[i][j] == SHIFT_NIGHT)
                {
                    System.out.print(" N ");
                }
                else if(roster[i][j] == SHIFT_OFF)
                {
                    System.out.print(" O ");
                }

                if(j == period -1)
                {
                    System.out.print("\n");
                }

            }
        }
        System.out.print("\n");
    }
    public void newPrintRoster(){
        for(int i = 0; i < nurses; i++)
        {
            for(int j = 0; j < period; j++)
            {
                if(j == 0)
                {
                    System.out.print("Nurse " +(i+1) + ":\t");
                }
                switch(roster[i][j]){
                    case (SHIFT_DAY): System.out.print(" D ");
                    case (SHIFT_NIGHT): System.out.print(" N ");
                    case (SHIFT_OFF): System.out.print(" O ");
                }
                if(j == period -1)
                {
                    System.out.print("\n");
                }
            }
        }
    }

    /**
     * Gets the completed roster from to be used to show the roster in a table.
     * @return 2D ArrayList of Strings example:
     * D D D N N O O
     * N N N 0 0 N N
     * ect.
     */
    public ArrayList<ArrayList<String>> getCompletedRoster(){

        ArrayList<ArrayList<String>> rosterArray = new ArrayList<ArrayList<String>>();

        for(int i = 0; i < nurses; i++)
        {
            ArrayList<String> row = new ArrayList<String>();

            for(int j = 0; j < period; j++)
            {
                if(roster[i][j] == SHIFT_DAY)
                {
                    row.add("D");
                }
                else if(roster[i][j] == SHIFT_NIGHT)
                {
                    row.add("N");
                }
                else if(roster[i][j] == SHIFT_OFF)
                {
                    row.add("O");
                }
            }
            rosterArray.add(row);
        }
        return rosterArray;
    }
}