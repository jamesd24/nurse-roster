package Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 10/6/13
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class Nurse {
   private String nurseName;
    private int id;
    private String qualification;
    private int shifts;
    private String shiftPattern;

    public Nurse(){
    }
    public Nurse(String nurseName, int id, String qualification, int shifts, String shiftPattern) {
        super();
        this.nurseName = nurseName;
        this.id = id;
        this.qualification = qualification;
        this.shifts = shifts;
        this.shiftPattern = shiftPattern;
    }
    public String getNurseName() {
        return nurseName;
    }
    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }
    public int getId() {
        return id;
    }
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }
    public int getShifts() {
        return shifts;
    }
    public void setShifts(int shifts) {
        this.shifts = shifts;
    }
    public String getShiftPattern() {
        return shiftPattern;
    }
    public void setShiftPattern(String shiftPattern) {
        this.shiftPattern = shiftPattern;
    }
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
