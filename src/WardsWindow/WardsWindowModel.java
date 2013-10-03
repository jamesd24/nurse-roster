package WardsWindow;

import Data.Ward;
import Data.XmlHandler;
import Data.Wards;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 9/26/13
 * Time: 12:25 PM
 * The Model gets the list of Wards for the XML and formats it to be used in the View List.
 */

public class WardsWindowModel {

    private Wards wardList;
    private XmlHandler xml = new XmlHandler();

    WardsWindowModel(){
        wardList = xml.getWardsFromXML();
    }

    public ArrayList<String> getWardList(){
        wardList = xml.getWardsFromXML();
        ArrayList<String> wardListString = new ArrayList<String>();
        for(int i = 0; i < wardList.getListOfWards().size() ; i++){
            wardListString.add(wardList.getListOfWards().get(i).getWardName() + " - " + wardList.getListOfWards().get(i).getRoster() + " Day Roster - Nurses:{NurseNumTODO}");
        }
        return wardListString;
    }
    public Ward getWardAt(int index){
        return wardList.getListOfWards().get(index);
    }
    public void deleteWard(int id){
        wardList = xml.getWardsFromXML();
        ArrayList<Ward> wl = wardList.getListOfWards();
        for(int i = 0; i < wl.size() ; ++i){
             if (wl.get(i).getId() == id){
                wl.remove(i);
             }
        }
        wl = resetIds(wl);
        wardList.setListOfWards(wl);
        xml.writeWardsToXML(wardList);
    }
    private ArrayList<Ward> resetIds(ArrayList<Ward> wl){
        for(int i = 0; i < wl.size() ; ++i){
            wl.get(i).setId(i);
        }
        return wl;
    }
}
