package assignment;

/**
 * Created by alexander on 20/10/15.
 */

// AIM:
// given some person instances it will create an xml file using
// auto generated classes (after executing look assignment.generated)
// Marshalling
import assignment.generated.*;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import java.util.GregorianCalendar;
public class Marshaller {
    public void generateXMLDocument(File xmlDocument) throws java.text.ParseException, DatatypeConfigurationException {
        try {

            // Creates a JAXB context

            JAXBContext jaxbContext = JAXBContext.newInstance("assignment.generated");
            javax.xml.bind.Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", true);
            assignment.generated.ObjectFactory factory = new assignment.generated.ObjectFactory();

            PeopleType people = factory.createPeopleType();

            // Creates three persons instances within the relative health profile

            // 1
            PersonType p = factory.createPersonType();
            p.setId(new BigInteger("1"));
            p.setFirstname("gianni");
            p.setLastname("morandi");
            p.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(1930, 2, 12)));
            HealthProfileType healthProfile1 = factory.createHealthProfileType();
            healthProfile1.setHeight(new Float("1.31"));
            healthProfile1.setWeight(new Float("42"));
            healthProfile1.setBmi(new Float("22.33"));
            healthProfile1.setLastupdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(2011, 3, 4, 0, 0, 0, 0, 0));
            p.setHealthprofile(healthProfile1);

            // 2
            PersonType p2 = factory.createPersonType();
            p2.setId(new BigInteger("2"));
            p2.setFirstname("Anna");
            p2.setLastname("Marini");
            p2.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(1970, 6, 3)));
            HealthProfileType healthProfile2 = factory.createHealthProfileType();
            healthProfile2.setHeight(new Float("1.77"));
            healthProfile2.setWeight(new Float("76"));
            healthProfile2.setBmi(new Float("53.63"));
            healthProfile2.setLastupdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(2013, 3, 6, 0, 0, 0, 0, 0));
            p2.setHealthprofile(healthProfile2);

            // 3
            PersonType p3 = factory.createPersonType();
            p3.setId(new BigInteger("3"));
            p3.setFirstname("Enza");
            p3.setLastname("Set");
            p3.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2000, 6, 15)));
            HealthProfileType healthProfile3 = factory.createHealthProfileType();
            healthProfile3.setHeight(new Float("1.35"));
            healthProfile3.setWeight(new Float("45"));
            healthProfile3.setBmi(new Float("3.25"));
            healthProfile3.setLastupdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(2009, 11, 9, 0, 0, 0, 0, 0));
            p3.setHealthprofile(healthProfile3);

            people.getPerson().add(p);
            people.getPerson().add(p2);
            people.getPerson().add(p3);

            // uses the java generics to create a custom JAXBElement
            // and then creates a file with a proper form

            JAXBElement<PeopleType> peopleElement = factory
                    .createPeople(people);
            System.out.println(peopleElement);
            marshaller.marshal(peopleElement,
                    new FileOutputStream(xmlDocument));

        } catch (IOException e) {
            System.out.println(e.toString());

        } catch (JAXBException e) {
            System.out.println(e.toString());

        }

    }



    public static void main(String[] argv) throws DatatypeConfigurationException, java.text.ParseException {

        //stores the output filename string in a variable
        String xmlDocument = "people1.xml";

        //creates an istance of itself
        Marshaller jaxbMarshaller = new Marshaller();

        // calls the method above
        jaxbMarshaller.generateXMLDocument(new File(xmlDocument));
    }
}
