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
        if(currentState == null)
        {
            currentState = initialState;
        }

        /**
         * If the current state is complete then return the result
         */
        if(currentState.isComplete())
        {
            return currentState;
        }

        /**
         * Get the location of the first empty nurse/shift in the roster
         */
        int[] emptyShift = currentState.getEmptyShift();

        /**
         * For each different type of shift
         */
        for(int shift : shiftTimes)
        {
            /**
             * If the shift type is valid then add it to the roster for that nurse and shift
             */
            if(currentState.checkValidAssignment(emptyShift[0], emptyShift[1], shift))
            {
                currentState.setNurseShift(emptyShift[0], emptyShift[1], shift);
            }

            // At this point we probably want to call some kind of paint method to display the updated roster so we can
            // visually see how the roster updates as it runs

            /**
             * Go around again with the new roster setup
             */
            Problem result = backtrackSearch(currentState, initialState);

            /**
             * If the result of the search was null then it failed and the shift type needs to be reset
             * If not then return the result
             */
            if(result != null)
            {
                return result;
            }
            else
            {
                currentState.setNurseShift(emptyShift[0], emptyShift[1], Roster.NOT_SET);
            }
        }

        return null;
    }
}
