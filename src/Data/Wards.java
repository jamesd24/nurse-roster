package Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 10/3/13
 * Time: 11:36 AM
 * Object Class used to store and handle list of wards.
 */
@XmlRootElement
public class Wards {
   private ArrayList<Ward> listOfWards;
    public Wards() {
    }
    public ArrayList<Ward> getListOfWards(){
       return listOfWards;
   }
    @XmlElement(name = "Ward")
    public void setListOfWards(ArrayList<Ward> listOfWards) {
        this.listOfWards = listOfWards;
    }
}