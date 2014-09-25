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

	boolean bTitle = false;
	
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
        if (qName.equalsIgnoreCase("Page")) {
                       
            infobox = new Infobox();
            if (infoboxList == null)
            	infoboxList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("title")) {
           
        	
        	bTitle = true;
        } 
    }
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Page")) {
          
        	infoboxList.add(infobox);
        }
    }
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    	ignorableWhitespace(ch, start,length);
         if (bTitle) {
            infobox.setTitle(new String(ch, start, length));
            bTitle = false;
        } 
    }
    


}
