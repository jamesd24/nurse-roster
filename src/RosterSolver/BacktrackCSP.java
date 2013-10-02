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
        /**if(!initialState.checkInitialState())
        {
            return null;
        }*/

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
            Problem result = null;
            /**
             * If the shift type is valid then add it to the roster for that nurse and shift
             */
            if(currentState.checkValidAssignment(emptyShift[0], emptyShift[1], shift))
            {
                currentState.setNurseShift(emptyShift[0], emptyShift[1], shift);

                result = backtrackSearch(currentState, initialState);

                // At this point we probably want to call some kind of paint method to display the updated roster so we can
                // visually see how the roster updates as it runs

                /**
                 * If the result of the search was null then it failed and the shift type needs to be reset
                 * If not then return the result
                 */
                if(result != null)
                {
                    return result;
                }
                //TODO Check this else out and see if I need to change it to be something else
                else
                {
                    currentState.setNurseShift(emptyShift[0], emptyShift[1], Roster.SHIFT_OFF);
                }
            }
        }

        return null;
    }
}
