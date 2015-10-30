//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.30 at 03:49:16 PM CET 
//


package assignment.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the assignment.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _People_QNAME = new QName("", "people");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: assignment.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PeopleType }
     * 
     */
    public PeopleType createPeopleType() {
        return new PeopleType();
    }

    /**
     * Create an instance of {@link HealthProfileType }
     * 
     */
    public HealthProfileType createHealthProfileType() {
        return new HealthProfileType();
    }

    /**
     * Create an instance of {@link PersonType }
     * 
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PeopleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "people")
    public JAXBElement<PeopleType> createPeople(PeopleType value) {
        return new JAXBElement<PeopleType>(_People_QNAME, PeopleType.class, null, value);
    }

}
