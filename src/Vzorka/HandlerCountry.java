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
	Help pomoc = new Help();
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
    
    //oparsovanie infoboxu country
    public boolean oparsujCountry(boolean flag, String vysledok){
   
    	String vystup = pomoc.PouziRegex("\\| ?conventional_long_name ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "conventional_long_name");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    		infobox.setTitle(vystup);
     	    flag = true;
    	}
    
    	vystup = pomoc.PouziRegex("\\| ?common_name ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "common_name");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setCommon_name(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?image_flag ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "image_flag");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setImage_flag(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?image_coat ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "image_coat");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setImage_coat(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?capital ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}|'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		if(vystup.contains("latd") ){
    			String[] parts = vystup.split("latd");
    			vystup = parts[0]; 
    		}
    		
    		vystup =pomoc.ocisti_retazec(vystup, "capital");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setCapital(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?official_religion ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "official_religion");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setOfficial_religion(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?official_languages ?= [A-Za-z0-9 _ =*.:?!()+-<>\\}\\[#@\\{|'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "official_languages");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setOfficial_languages(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?government_type ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[ #@\\{}'`$%^&;<>,����|]+", vysledok);  	
    	if (vystup != null){
    		if(vystup.contains("leader_title") ){
    			String[] parts = vystup.split("leader_title");
    			vystup = parts[0]; 
    		}
    		
    		vystup =pomoc.ocisti_retazec(vystup, "government_type");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! |+-]","");
    		vystup = vystup.replaceAll("\\|"," ");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setGovernment_type(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?area_km2 ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "area_km2");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setArea_km2(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?area_sq_mi ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "area_sq_mi");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setArea_sq_mi(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?population_estimate ?= [A-Za-z0-9 _ =*.:?!()+->\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "population_estimate");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setPopulation_estimate(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?population_estimate_rank ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "population_estimate_rank");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setPopulation_estimate_rank(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?currency_code ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		
    		vystup =pomoc.ocisti_retazec(vystup, "currency_code");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
    	    infobox.setCurrency_code(vystup);
     	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?currency ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����|]+", vysledok);  	
    	if (vystup != null){
    		if(vystup.contains("currency_code") ){
    			String[] parts = vystup.split("currency_code");
    			vystup = parts[0]; 
    		}
    		vystup =pomoc.ocisti_retazec(vystup, "currency");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = vystup.replaceAll("  "," ");
    		vystup = pomoc.posledna_medzera(vystup);
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
        	//zarovnanie do jedneho riadku
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); 
        	//odstranenie nepotrebnych medzier
        	vysledok = vysledok.trim().replaceAll(" +", " "); 
        	//vybratie infoboxu country
        	String text = pomoc.PouziRegex("\\{\\{Infobox country \\s*(.*)", vysledok);

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
