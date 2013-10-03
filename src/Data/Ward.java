package Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 10/3/13
 * Time: 12:55 PM
 * Class used to store the list of Wards
 */
@XmlRootElement
public class Ward {
     private String wardName;
     private int id;
     private int roster;
    public Ward(){
    }
    public Ward(String wardName, int roster, int id) {
        super();
        this.wardName = wardName;
        this.id = id;
        this.roster = roster;
    }
    public String getWardName() {
        return wardName;
    }
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    public int getRoster() {
        return roster;
    }
    public void setRoster(int roster) {
        this.roster = roster;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

