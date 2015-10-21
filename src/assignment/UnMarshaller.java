package assignment;

import assignment.generated.PeopleType;
import assignment.generated.PersonType;
import org.xml.sax.SAXException;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

/**
 * Created by alexander on 20/10/15.
 */

// AIM:
// given a well formed xml file it creates person instances
// Unmarshalling


public class UnMarshaller {
    public void unMarshall(File xmlDocument) {
        try {

            // creates an JAXBContext object
            JAXBContext jaxbContext = JAXBContext.newInstance("assignment.generated");

            Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            // Uses the people.xsd schema to validate the XML file given as input
            Schema schema = schemaFactory.newSchema(new File("people.xsd"));
            unMarshaller.setSchema(schema);

            CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
            unMarshaller.setEventHandler(validationEventHandler);

            @SuppressWarnings("unchecked")
            JAXBElement<PeopleType> peopleElement = (JAXBElement<PeopleType>) unMarshaller
                    .unmarshal(xmlDocument);

            PeopleType peopleType = peopleElement.getValue();

            // creates a list of PeopleType Type
            List<PersonType> peopleList = peopleType.getPerson();
            for (int i = 0; i < peopleList.size(); i++) {

                PersonType p = (PersonType) peopleList.get(i);

                System.out.println(p.getId());
                System.out.println(p.getLastname());
                System.out.println(p.getFirstname());
                System.out.println(p.getBirthdate().toString());
                System.out.println(p.getHealthprofile().getHeight());
                System.out.println(p.getHealthprofile().getWeight());
                System.out.println(p.getHealthprofile().getBmi());
                System.out.println(p.getHealthprofile().getLastupdate().toString());


            }
        } catch (JAXBException e) {
            System.out.println(e.toString());
        } catch (SAXException e) {
            System.out.println(e.toString());
        }
    }
    // main method
    public static void main(String[] argv) {
        File xmlDocument = new File("people1.xml");
        UnMarshaller jaxbUnmarshaller = new UnMarshaller();

        jaxbUnmarshaller.unMarshall(xmlDocument);

    }

    class CustomValidationEventHandler implements ValidationEventHandler {
        public boolean handleEvent(ValidationEvent event) {
            if (event.getSeverity() == ValidationEvent.WARNING) {
                return true;
            }
            if ((event.getSeverity() == ValidationEvent.ERROR)
                    || (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

                System.out.println("Validation Error:" + event.getMessage());

                ValidationEventLocator locator = event.getLocator();
                System.out.println("at line number:" + locator.getLineNumber());
                System.out.println("Unmarshalling Terminated");
                return false;
            }
            return true;
        }

    }
}
