package Vzorka;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerCountry  extends DefaultHandler {
	
	private List<Infobox_country> infoboxList = null;
	private Infobox_country infobox = null;
	private StringBuffer sb;
	
	public List<Infobox_country> getInfoboxList() {
        return infoboxList;
    }

	boolean bTitle = false;
	
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
    	sb = new StringBuffer();
        if (qName.equalsIgnoreCase("Page")) {                
            infobox = new Infobox_country();
            if (infoboxList == null) {
            	infoboxList = new ArrayList<>();
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
    
    public boolean oparsujCountry(boolean flag, String vysledok){
    	
    	//country
    	String vystup = PouziRegex("\\| ?conventional_long_name = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setTitle(vystup);
     	    flag = true;
    	}
    
    	vystup = PouziRegex("\\| ?common_name = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setCommon_name(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?image_flag = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setImage_flag(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?image_coat = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setImage_coat(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?capital = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setCapital(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?official_religion = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setOfficial_religion(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?official_languages = [A-Za-z0-9 _ =*.:?!()+-<>\\}\\[#@\\{|'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setOfficial_languages(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?government_type = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[ #@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setGovernment_type(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?area_km2 = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setArea_km2(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?area_sq_mi = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setArea_sq_mi(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?population_estimate = [A-Za-z0-9 _ =*.:?!()+->\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setPopulation_estimate(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?population_estimate_rank = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setPopulation_estimate_rank(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?currency_code = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setCurrency_code(vystup);
     	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?currency = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+ ", vysledok);  	
    	if (vystup != null){
    		//System.out.print(vystup);
    	    infobox.setCurrency(vystup);
     	    flag = true;
    	}
    	
    	return flag;
    }
    
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	boolean flag_country = false;
    	
        if (bTitle) {
          
        	String vysledok = sb.toString();
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); //zarovnanie do jedneho riadku
        	vysledok = vysledok.trim().replaceAll(" +", " "); //odstranenie nepotrebnych medzier
        	
        	String text = PouziRegex("\\{\\{Infobox country \\s*(.*)", vysledok);
        	
        	
        	if (text !=null){
        	flag_country  = oparsujCountry(flag_country, text);
        	}
        	if (flag_country == true){
        		if ( infobox.getTitle()!= null ||  infobox.getCommon_name() != null){
        			System.out.println(infobox.getTitle()+" "
        		
        					+infobox.getCommon_name() +" "
        					+infobox.getImage_flag()+" "
        					+infobox.getImage_coat()+" "
        					+infobox.getCapital()+" "
        					+infobox.getOfficial_religion()+" "
        					+infobox.getOfficial_languages()+" "
        					+infobox.getGovernment_type()+" "
        					+infobox.getArea_km2()+" "
        					+infobox.getArea_sq_mi()+" "
        					+infobox.getCurrency()+" "
        					+infobox.getCurrency_code()+" "
        					+infobox.getPopulation_estimate()+" "
        					+infobox.getPopulation_estimate_rank()
        					);
        			
        			infoboxList.add(infobox);
            	       			
        		}
        	}	

            bTitle = false;
            flag_country = false;     	 
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
