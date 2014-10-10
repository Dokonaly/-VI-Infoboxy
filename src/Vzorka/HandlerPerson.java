package Vzorka;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerPerson  extends DefaultHandler {
	
	private List<Infobox_person> infoboxPersonList = null;
	private Infobox_person infoboxPerson = null;
	private StringBuffer sb;
	
	public List<Infobox_person> getInfoboxList() {
        return infoboxPersonList;
    }

	boolean bTitle = false;
	
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
    	sb = new StringBuffer();
        if (qName.equalsIgnoreCase("Page")) {                
        	infoboxPerson = new Infobox_person();
            if (infoboxPersonList == null) {
            	infoboxPersonList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("text")) {
        	bTitle = true;
        } 
    }
 
    public String PouziRegex(String regex, String vstup){
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(vstup);
    	if (matcher.find())
    	{
    		return matcher.group(0);
    	}
    	return null;
    }
    
    public boolean oparsujPerson(String vysledok){
    	boolean flag = false;
    	
    	String vystup = PouziRegex("\\| ?name = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  
    	
    	if (vystup != null){
    		vystup = vystup.replace("|name = ", "");
    		vystup = vystup.replace("| name = ", "");
    		infoboxPerson.setName(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?image_size = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|image_size = ", "");
    		vystup = vystup.replace("| image_size = ", "");
    		infoboxPerson.setImage_size(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?image = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|image = ", "");
    		vystup = vystup.replace("| image = ", "");
    		infoboxPerson.setImage(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?birth_date = [^}]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|birth_date = ", "");
    		vystup = vystup.replace("| birth_date = ", "");
    		infoboxPerson.setBirth_date(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?birth_place = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|birth_place = ", "");
    		vystup = vystup.replace("| birth_place = ", "");
    		infoboxPerson.setBirth_place(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?death_date = [^}]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|death_date = ", "");
    		vystup = vystup.replace("| death_date = ", "");
    		infoboxPerson.setDeath_date(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?death_place = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|death_place = ", "");
    		vystup = vystup.replace("| death_place = ", "");
    		infoboxPerson.setDeath_place(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?occupation = [A-Za-z0-9 _ =*.:?!()+-<>\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|occupation = ", "");
    		vystup = vystup.replace("| occupation = ", "");
    		infoboxPerson.setOccupation(vystup);
    	    flag = true;
    	}
    
      	return flag;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	boolean flag_person = false;
    	
        if (bTitle) {
          
        	String vysledok = sb.toString();
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); //zarovnanie do jedneho riadku
        	vysledok = vysledok.trim().replaceAll(" +", " "); //odstranenie nepotrebnych medzier
        	
        	String text = PouziRegex("\\{\\{Infobox person \\s*(.*)", vysledok);
        
        	if (text !=null){
        		flag_person  = oparsujPerson(text);
        	}
        	if (flag_person == true){
        		if ( infoboxPerson.getName()!= null){
        			
        			System.out.println(infoboxPerson.getName()+" "
        					+infoboxPerson.getImage()+" "
        					+infoboxPerson.getImage_size()+" "
        					+infoboxPerson.getBirth_date()+" "
        					+infoboxPerson.getBirth_place()+" "
        					+infoboxPerson.getDeath_date()+" "
        					+infoboxPerson.getDeath_place()+" "
        					+infoboxPerson.getOccupation()
    	        			);
        			
        			infoboxPersonList.add(infoboxPerson);    			
        		}
        	}	
            bTitle = false;
            flag_person = false;     	 
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