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
 * Model for WardPP used to new ward and existing ward data. Both WardPP and NursePP use this Model.
 */
public class WardPPModel {
    public Wards wardList;
    public ArrayList<Nurse> nurseList;
    private XmlHandler xml = new XmlHandler();

    WardPPModel(Ward w){
        //Properties
        wardList = xml.getWardsFromXML();
        nurseList = w.getListOfNurses();
    }
    WardPPModel(){
        //New Ward
        wardList = xml.getWardsFromXML();
        setupDefaultNurses();
    }

    /**
     * Saves new ward to the data.xml file.
     * @param name
     * @param roster
     */
    public void newWardDataToXML(String name, int roster){
        Ward newWard = new Ward(name,roster,generateNewId(), nurseList);
        ArrayList<Ward> w = wardList.getListOfWards();
        w.add(newWard);
        wardList.setListOfWards(w);
        xml.writeWardsToXML(wardList);
    }

    /**
     * replaces an existing ward with new data and writes it to the data.xml
     * @param name
     * @param roster
     * @param w
     */
    public void replaceWardData(String name, int roster,Ward w){
        Ward newWard = new Ward(name,roster,w.getId(), nurseList);
        ArrayList<Ward> wardArray = wardList.getListOfWards();
        wardArray.set(w.getId(), newWard);
        wardList.setListOfWards(wardArray);
        xml.writeWardsToXML(wardList);
    }

    /**
     * Generates new id for a new ward when writing to data.xml.
     * @return id for ward
     */
    private int generateNewId(){
        ArrayList<Ward> w = wardList.getListOfWards();
    int id = w.get(w.size()-1).getId()+1;
    return id;
    }

    /**
     * Generates an id for a new nurse.
     * @return id for nurse
     */
    public int generateNurseId(){
        int id = nurseList.get(nurseList.size()-1).getId()+1;
        return id;
    }

    /**
     * Used to set up default nurse data when creating a new ward.
     */
    private void setupDefaultNurses(){
        ArrayList<Nurse> nurses = new ArrayList<Nurse>();
        Nurse testNurse1 = new Nurse("TestNurse1", 0, "SRN",5,"D");
        Nurse testNurse2 = new Nurse("TestNurse2", 1, "SRN",5,"DN");
        Nurse testNurse3 = new Nurse("TestNurse3", 2, "SRN",5,"DN");
        Nurse testNurse4 = new Nurse("TestNurse4", 2, "SRN",5,"DN");
        Nurse testNurse5 = new Nurse("TestNurse5", 3, "RN",5,"D");
        Nurse testNurse6 = new Nurse("TestNurse6", 4, "RN",5,"D");
        Nurse testNurse7 = new Nurse("TestNurse7", 5, "RN",5,"N");
        nurses.add(testNurse1);
        nurses.add(testNurse2);
        nurses.add(testNurse3);
        nurses.add(testNurse4);
        nurses.add(testNurse5);
        nurses.add(testNurse6);
        nurses.add(testNurse7);
        nurseList = nurses;
    }

    /**
     * Used to create information used later in the view nurse list.
     * @return nList as a String ArrayList.
     */
    public ArrayList<String> getNurseList(){
        ArrayList<String> nList = new ArrayList<String>();
        for(int i = 0; i < nurseList.size() ; i++){
            nList.add(nurseList.get(i).getNurseName() + " - " + nurseList.get(i).getQualification() + " - "+ nurseList.get(i).getShiftPattern()+ " - " +nurseList.get(i).getShifts());
        }
        return nList;
    }

    /**
     * Used to reconfigure the id's of the nurse list.
     * @param nl existing nurse list.
     * @return ArrayList<Nurse>
     */
    public ArrayList<Nurse> resetIds(ArrayList<Nurse> nl){
        for(int i = 0; i < nl.size() ; ++i){
            nl.get(i).setId(i);
        }
        return nl;
    }
}
