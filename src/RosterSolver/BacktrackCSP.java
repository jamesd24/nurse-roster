package RosterSolver;
/**
 * Created with IntelliJ IDEA.
 * User: Phil
 * Date: 9/23/13
 * Time: 8:43 PM
 *
 * Takes an initial roster state and return the complete roster
 */
public class BacktrackCSP
{
    private static Problem solution = null;

    private static final int[] shiftTimes = {Roster.SHIFT_DAY,
                                Roster.SHIFT_NIGHT,
                                Roster.SHIFT_OFF};

    public static Problem rosterSolver(Problem initialState)
    {
        // If the setup of the roster isn't correct, don't proceed
        if(!initialState.checkInitialState())
        {
            return null;
        }

        return backtrackSearch(solution, initialState);
    }

    /**
     * Takes two problem objects and returns either a completed roster or failure
     */
    private static Problem backtrackSearch(Problem currentState, Problem initialState)
    {
        /**
         *  setup the current state for the first run through
         */
        //TODO this needs changing to be a method to copy the contents of initialState into currentState so it isn't just a reference that updates both objects
        //TODO right now the initialState is useless as it is the same as the currentState, inititalState is only really needed for the print process anyway where
        //TODO you display the set shifts in red, so this could be changed for a list of shifts which were set and initialState removed
        if(currentState == null)
        {
            currentState = initialState;
        }

        /**
         * If the current state is complete then return the result
         */
        //TODO update the isComplete() method to check if the day assignments are correct
        //TODO i.e. enough people on each day, and the right number of SRNs etc
        if(currentState.isComplete())
        {
            return currentState;
        }

        /**
         * Get the location of the first empty nurse/shift in the roster
         */
        int[] emptyShift = currentState.getEmptyShift();

        /**
         * If there is another empty shift then keep filling them
         */
        if(emptyShift[0] != -1)
        {
            /**
             * For each different type of shift
             */
            for(int shift : shiftTimes)
            {
                Problem result = null;
                /**
                 * If the shift type is valid then add it to the roster for that nurse and shift
                 */
                if(currentState.checkValidAssignment(emptyShift[0], emptyShift[1], shift))
                {
                    currentState.setNurseShift(emptyShift[0], emptyShift[1], shift);

                    //TODO PRINT FUNCTION GOES HERE TO DISPLAY UPDATING VIEW
                    //currentState.printRoster();
                    //System.out.print("\n");

                    result = backtrackSearch(currentState, initialState);

                    /**
                     * If the result of the search was null then it failed and the shift type needs to be reset
                     * If not then return the result
                     */
                    if(result != null)
                    {
                        return result;
                    }
                    //TODO I think this is correct now, not certain, needs further investigation
                    else
                    {
                        currentState.setNurseShift(emptyShift[0], emptyShift[1], Roster.NOT_SET);
                    }
                }
            }
        }
        /**
         * Something isn't correct in the day assignments
         */
        else
        {
            return null;
        }


        return null;
    }
}
