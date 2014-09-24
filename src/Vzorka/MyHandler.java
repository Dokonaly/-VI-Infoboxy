package Vzorka;

import java.util.ArrayList;
import java.util.List;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Employee.Employee;

public class MyHandler  extends DefaultHandler {
	
	private List<Infobox> infoboxList = null;
	private Infobox infobox = null;
	
	public List<Infobox> getInfoboxList() {
        return infoboxList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
        if (qName.equalsIgnoreCase("Employee")) {
            //create a new Employee and put it in Map
            String id = attributes.getValue("id");
            //initialize Employee object and set id attribute
            emp = new Employee();
            emp.setId(Integer.parseInt(id));
            //initialize list
            if (empList == null)
                empList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("name")) {
            //set boolean values for fields, will be used in setting Employee variables
            bName = true;
        } else if (qName.equalsIgnoreCase("age")) {
            bAge = true;
        } else if (qName.equalsIgnoreCase("gender")) {
            bGender = true;
        } else if (qName.equalsIgnoreCase("role")) {
            bRole = true;
        }
    }
 
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Employee")) {
            //add Employee object to list
            empList.add(emp);
        }
    }
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
 
        if (bAge) {
            //age element, set Employee age
            emp.setAge(Integer.parseInt(new String(ch, start, length)));
            bAge = false;
        } else if (bName) {
            emp.setName(new String(ch, start, length));
            bName = false;
        } else if (bRole) {
            emp.setRole(new String(ch, start, length));
            bRole = false;
        } else if (bGender) {
            emp.setGender(new String(ch, start, length));
            bGender = false;
        }
    }

}
