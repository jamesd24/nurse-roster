package WardPP;

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
        Ward newWard = new Ward(name,roster,generateNewId());
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
