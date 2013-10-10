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

        return backtrackSearch(initialState);
    }

    /**
     * Takes two problem objects and returns either a completed roster or failure
     */
    private static Problem backtrackSearch(Problem currentState)
    {
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
         * If there is another empty shift then keep filling them
         */
        //TODO Check if I need this
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

                    // If the nurse selected is the last one in the day check the day is complete
                    if(emptyShift[0] == currentState.getNumNurses() -1)
                    {
                        // If the day is fine then continue
                        if(currentState.checkDay(emptyShift[1]))
                        {

                            //currentState.printRoster();
                            result = backtrackSearch(currentState);

                            if(result != null)
                            {
                                return result;
                            }
                            //TODO This is maybe wrong
                            else
                            {
                                currentState.setNurseShift(emptyShift[0], emptyShift[1], Roster.NOT_SET);
                            }
                        }
                        else
                        {
                            currentState.setNurseShift(emptyShift[0], emptyShift[1], Roster.NOT_SET);
                        }
                    }
                    else
                    {
                        //currentState.printRoster();

                        result = backtrackSearch(currentState);

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
