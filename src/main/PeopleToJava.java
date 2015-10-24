
package main;

import models.HealthProfile;
import models.Person;
import javax.xml.*;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.util.List;
import models.HealthProfile.*;

import java.io.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;



/**
 * Created by alexander on 21/10/15.
 */

public class PeopleToJava {
    public static PeopleStore people = new PeopleStore();

    public static void main() throws Exception {

        HealthProfileReader hPR = new HealthProfileReader();
        List<Person> peopleList = hPR.getAllPersonsObjects();
        for(int i=0; i<peopleList.size(); i++ ){
            people.getData().add(peopleList.get(i));
            
        }
        ObjectMapper mapper = new ObjectMapper();

        // Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();

        // configure as necessary
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
		
    }

}
