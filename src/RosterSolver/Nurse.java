package RosterSolver;
/**
 * Created with IntelliJ IDEA.
 * User: Phil
 * Date: 9/23/13
 * Time: 11:03 AM
 *
 * A Nurse object type that defines the shift pattern and the grade of a nurse
 * As well as the number of since last day off
 */
public class Nurse
{
    // Grades
    public static final int SRN = 1;
    public static final int RN = 2;

    //Shift Patterns
    public static final int D = 4;
    public static final int N = 5;
    public static final int DN = 6;

    // Nurse grade
    private int grade;
    // Nurse Shift type
    private int shift;

    // Days since last day off
    private int lastOff = 0;
    // The last shift the nurse was assigned
    private int lastShift = Roster.SHIFT_OFF;

    public Nurse()
    {
    }

    public int getShiftType()
    {
        return shift;
    }

    public void setShiftType(int shift)
    {
        this.shift = shift;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    public int getLastOff()
    {
        return lastOff;
    }

    public void setLastOff(int day)
    {
        this.lastOff = day;
    }

    public int getLastShift()
    {
        return lastShift;
    }

    public void setLastShift(int shift)
    {
        this.lastShift = shift;
    }

}
