package Data;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Created with IntelliJ IDEA.
 * User: James
 * Date: 10/3/13
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */

public class XmlHandler {
    public void writeWardsToXML(Wards wards){
        try{
            File file = new File("data.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Wards.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(wards, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Wards getWardsFromXML(){
        try {

            File file = new File("data.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Wards.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Wards wards = (Wards) jaxbUnmarshaller.unmarshal(file);
            return wards;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
