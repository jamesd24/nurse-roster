package RosterSolver;
/**
 * Created with IntelliJ IDEA.
 * User: Phil
 * Date: 9/23/13
 * Time: 11:03 AM
 *
 * A Nurse object type that defines the shift pattern and the grade of a nurse
 */
public class Nurse
{
    // Grade
    public static final int SRN = 1;
    public static final int RN = 2;

    //Shift Pattern
    public static final int D = 4;
    public static final int N = 5;
    public static final int DN = 6;

    private int grade;
    private int shift;

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

}
