package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import models.HealthProfile;
import models.Person;


public class HealthProfileReader {
	
    Document doc;
    XPath xpath;

    

	public List<Person> getAllPersonsObjects() throws XPathExpressionException {

		XPathExpression expr = xpath.compile ("//person");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		List<Person> personList = new ArrayList<Person>();
		System.out.println(nodes.getLength());

		Node node;

		for (int p = 0; p < nodes.getLength(); p++) {
			node = nodes.item(p);
			if (node!=null){
				Long pID = Long.valueOf(node.getAttributes().getNamedItem("id").getNodeValue());
				NodeList attrList = node.getChildNodes();
				Node prop;
				String xName = null;
				String xSurname = null;
				String xbirth = null;
				double h = 0;
				double w = 0;
				String xuDate = null;
				double xbmi = 0;
				for(int i=0; i < attrList.getLength(); i++){
					prop = attrList.item(i);
					if(prop.getNodeName()=="firstname"){
						xName = prop.getTextContent();

					}else if(prop.getNodeName()=="lastname"){
						xSurname= prop.getTextContent();
					}else if(prop.getNodeName()=="birthdate"){
						xbirth = prop.getTextContent();
					}else if(prop.getNodeName()=="healthprofile"){
						NodeList hpAttributesList = prop.getChildNodes();
						for (int j = 0; j< hpAttributesList.getLength(); j++ ){
							Node hpProp = hpAttributesList.item(j);
							if(hpProp.getNodeName()=="weight"){ w = Double.valueOf(hpProp.getTextContent());}
							if(hpProp.getNodeName()=="height"){ h = Double.valueOf(hpProp.getTextContent());}
							if(hpProp.getNodeName()=="lastupdate"){ xuDate = hpProp.getTextContent();}
							if(hpProp.getNodeName()=="bmi"){ xbmi = Double.valueOf(hpProp.getTextContent());}
						}
					}
				}

				HealthProfile hp = new HealthProfile(w, h, xbmi, xuDate);
				//System.out.println(hp);
				personList.add(new Person(xName,xSurname,hp,xbirth, pID));
			}
		}
		for(Person pers : personList){
			System.out.println(pers.getId() + " " + pers.getFirstname() + " " + pers.getLastname());
			System.out.println(pers.getBirthday());
			System.out.println(pers.gethProfile());
		}
		return personList;
	}

	// this function load the .xml file
    public void loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse("people.xml");

        //creating xpath object
        getXPathObj();
    }

	//this function returns an xpath object, it is used to evaluate XPATH expressions
    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }

	//main method

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		HealthProfileReader hPR = new HealthProfileReader();
		hPR.loadXML();

		int argCount = args.length;
		if (argCount == 0) {
			System.out.println("Please give me at least one parameter");

			// handles the following command:  gw|gh    <|>|=    value
			// gw  stands for getWeight
			// gh  stands for getHeight
			// then it need an operator among <,> or =
			// then it need a value
			// read the function comment for further informations

		} else if (argCount == 3) {
			String method = args[0];
			String operator = args[1];
			String value = args[2];
			if(method.equals("gh")){
				hPR.getHeightOp(operator,value);
			} else if(method.equals("gw")){
				hPR.getWeightOp(operator,value);
			}
		// handles when we pass one parameter to the main method

		} else if(argCount == 1){

			// option getall prints all people details

			if(args[0].equals("getall")){
				hPR.getAllPeople();
			} else {

				// if we pass an ID as parameter it will print the Health Profile
				// of the given ID, it prints an error if ID does not exists

				System.out.println("\n\nThe health profile of the given id is:");
				hPR.displayHealthProfileX(args[0]);
			}
		} else if (argCount == 2) {

			// it reads the first parameter and then it
			// recognizes wich method we would like to execute gw or gh
			// for the given ID

			if(args[0].equals("gh")){
					System.out.println("\n \nThe Height for the given id is:");
					hPR.getxHeight(args[1]);
				}

			else if(args[0].equals("gw")){
					System.out.println(" \n\nThe Weight for the given id is:");
					hPR.getXWeight(args[1]);
				}

		} else {
			// Handles malformed args when executing main method
			System.out.println("BAD COMMAND");
			}
		}

	// prints all the people details using XPATH

	private void getAllPeople() throws XPathExpressionException{
		XPathExpression expr = xpath.compile ("//person");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for(int i=0; i<nodes.getLength(); i++){
			System.out.println(nodes.item(i).getTextContent());
		}
	}

	// prints the Health Profile of a given ID using XPATH

	private void displayHealthProfileX(String PId) throws XPathExpressionException{
		XPathExpression expr = xpath.compile("//person[@id='" + PId + "']/healthprofile");
		//("//person[weight " + operator + "'" + value + "']");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for(int i=0; i<nodes.getLength(); i++){
			System.out.println(nodes.item(i).getTextContent());
		}
		if(nodes.getLength()==0){System.out.println("\n \n The given ID is not in our database.");}
	}

	// prints the Weight of a given ID using XPATH

	private void getXWeight(String id) throws XPathExpressionException{
		XPathExpression expr = xpath.compile ("/people/person[@id='"+id+"']/healthprofile/weight");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
			System.out.println(nodes.item(0).getTextContent());
		}

	}

	// prints the Height of a given ID using XPATH

	private void getxHeight(String id) throws XPathExpressionException {
		XPathExpression expr = xpath.compile ("/people/person[@id='"+id+"']/healthprofile/height");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
			System.out.println(nodes.item(0).getTextContent());
		}
	}


	// prints all people details if the person fullfill certains conditions
	// e.g.: height > 1.55 or height = 1.75 or or Height < 1.78


	private void getHeightOp(String operator, String value) throws XPathExpressionException {
		XPathExpression expr = xpath.compile("//person[healthprofile/height " + operator + "'" + value + "']");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for(int i=0; i<nodes.getLength(); i++){
			System.out.println(nodes.item(i).getTextContent());
		}
	}

	// prints all people details if the person fullfill certains conditions
	// e.g.: Weight > 55 or Weight = 78 or Weight < 78

	private void getWeightOp(String operator, String value) throws XPathExpressionException {
		XPathExpression expr = xpath.compile ("//person[healthprofile/weight "+ operator + "'" + value + "']"); //("//person[weight " + operator + "'" + value + "']");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for(int i=0; i<nodes.getLength(); i++){
			System.out.println(nodes.item(i).getTextContent());
		}
	}

}
