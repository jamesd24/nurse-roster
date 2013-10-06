package WardPP;

import Data.Nurse;
import Data.Ward;
import Data.Wards;
import Data.XmlHandler;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/29/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class WardPPModel {
    private Wards wardList;
    private XmlHandler xml = new XmlHandler();

    WardPPModel(){
        wardList = xml.getWardsFromXML();
    }
    public void newWardDataToXML(String name, int roster){
        ArrayList<Nurse> nurses = new ArrayList<Nurse>();
        Nurse testNurse1 = new Nurse("TestNurse1", 0, "SRN",5,"DN");
        Nurse testNurse2 = new Nurse("TestNurse2", 1, "RN",5,"DN");
        nurses.add(testNurse1);
        nurses.add(testNurse2);

        Ward newWard = new Ward(name,roster,generateNewId(), nurses);
        ArrayList<Ward> w = wardList.getListOfWards();
        w.add(newWard);
        wardList.setListOfWards(w);
        xml.writeWardsToXML(wardList);
    }
    private int generateNewId(){
        ArrayList<Ward> w = wardList.getListOfWards();
    int id = w.get(w.size()-1).getId()+1;
    return id;
}
}
