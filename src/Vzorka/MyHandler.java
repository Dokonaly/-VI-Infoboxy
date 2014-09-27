package Vzorka;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
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
          
        	String vysledok = sb.toString();
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); //zarovnanie do jedneho riadku
        	vysledok = vysledok.trim().replaceAll(" +", " "); //odstranenie nepotrebnych medzier
        	Pattern pattern = Pattern.compile(
        			"\\{\\{Infobox disease [A-Za-z0-9 |_ =*.:?!()+-<>\\]\\[#@\\{'`$%^&;<>,ֹציז]+ " +
        			"| \\{\\{Infobox person [A-Za-z0-9 |_ =*.:?!()+-<>\\]\\[#@\\{'`$%^&;<>,ֹציז]+ " +
        			"| \\{\\{Infobox book [A-Za-z0-9 |_ =*.:?!()+-<>\\]\\[#@\\{'`$%^&;<>,ֹציז]+" +
        			"| \\{\\{Infobox award [A-Za-z0-9 |_ =*.:?!()+-<>\\]\\[#@\\{'`$%^&;<>,ֹציז]+ "			
        			); //}
        	Matcher matcher = pattern.matcher(vysledok);
        	if (matcher.find())
        	{
        	    System.out.println(matcher.group(0));
        	    //infobox.setTitle(vysledok);
        	    infobox.setTitle(matcher.group(0));
         	    infoboxList.add(infobox);
        	}
        	
            bTitle = false;
        	
        	 
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
