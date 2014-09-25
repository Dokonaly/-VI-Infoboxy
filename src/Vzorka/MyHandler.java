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
	private StringBuffer sb;
	
	public List<Infobox> getInfoboxList() {
        return infoboxList;
    }

	boolean bTitle = false;
	
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
    	sb = new StringBuffer();
        if (qName.equalsIgnoreCase("Page")) {                
            infobox = new Infobox();
            if (infoboxList == null) {
            	infoboxList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("text")) {
        	bTitle = true;
        } 
    }
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bTitle) {
          
        	infobox.setTitle(sb.toString());
            bTitle = false;
        	infoboxList.add(infobox);
        	 
        }
       
       
    }
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    
    	 
         if (sb!=null && bTitle) {
             for (int i=start; i<start+length; i++) {
            	 sb.append(ch[i]);
             }
             
         }
         
    }
    


}
