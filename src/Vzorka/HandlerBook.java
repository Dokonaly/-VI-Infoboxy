package Vzorka;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerBook  extends DefaultHandler {
	
	private List<Infobox_book> infoboxBookList = null;
	private Infobox_book infoboxBook = null;
	private StringBuffer sb;
	Help pomoc = new Help();
	public List<Infobox_book> getInfoboxList() {
        return infoboxBookList;
    }

	boolean bTitle = false;
	
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
    	sb = new StringBuffer();
        if (qName.equalsIgnoreCase("Page")) {                
        	infoboxBook = new Infobox_book();
            if (infoboxBookList == null) {
            	infoboxBookList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("text")) {
        	bTitle = true;
        } 
    }
 
   
    //oparsovanie infoboxu book
    public boolean oparsujBook(String vysledok){
    	boolean flag = false;
    
    	String vystup = pomoc.PouziRegex("\\| ?name ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "name");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setName(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?translator ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "translator");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setTranslator(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?image ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "image");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setImage(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?caption ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "caption");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setCaption(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?author ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "author");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setAuthor(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?country ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "country");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setCountry(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?language ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����|]+", vysledok);  	
    	if (vystup != null){
    		
    		if(vystup.contains("series") ){
    			String[] parts = vystup.split("series");
    			vystup = parts[0]; 
    		}
    		
    		if(vystup.contains("subject") ){
    			String[] parts = vystup.split("subject");
    			vystup = parts[0]; 
    		}
    		
    		if(vystup.contains("genre") ){
    			String[] parts = vystup.split("genre");
    			vystup = parts[0]; 
    		}

    		vystup = pomoc.ocisti_retazec(vystup, "language");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]|","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setLanguage(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?subject ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "subject");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setSubject(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?genre ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "genre");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-\\[\\]]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setGenre(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?published ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "published");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setPublished(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?media_type ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "media_type");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setMedia_type(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?pages ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "pages");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setPages(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?isbn ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "isbn");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setIsbn(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?preceded_by ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup = pomoc.ocisti_retazec(vystup, "preceded_by");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setPreceded_by(vystup);
    	    flag = true;
    	}
    	
    	vystup = pomoc.PouziRegex("\\| ?followed_by ?= [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,����]+", vysledok);  	
    	if (vystup != null){
    		vystup =pomoc.ocisti_retazec(vystup, "followed_by");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    		vystup = pomoc.posledna_medzera(vystup);
    		infoboxBook.setFollowed_by(vystup);
    	    flag = true;
    	}
    	
    	return flag;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	boolean flag_book = false;
    	
    	
        if (bTitle) {
        	
        	String vysledok = sb.toString();
        	//zarovnanie do jedneho riadku
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); 
        	//odstranenie nepotrebnych medzier
        	vysledok = vysledok.trim().replaceAll(" +", " ");
        	//vybratie infoboxu book
        	String text = pomoc.PouziRegex("\\{\\{Infobox book\\s*(.*)", vysledok);
        
        	if (text !=null){
        		flag_book  = oparsujBook(text);
        	}
        	if (flag_book == true){
        		if ( infoboxBook.getName()!= null && infoboxBook.getAuthor() != null){
        			
        			System.out.println(infoboxBook.getName()+" "
        					+infoboxBook.getAuthor() +" "
        					+infoboxBook.getTranslator()+" "
        					+infoboxBook.getImage()+" "
        					+infoboxBook.getCaption()+" "
        					+infoboxBook.getCountry()+" "
        					+infoboxBook.getLanguage()+" "
        					+infoboxBook.getSubject()+" "
        					+infoboxBook.getGenre()+" "
        					+infoboxBook.getPublished()+" "
        					+infoboxBook.getMedia_type()+" "
        					+infoboxBook.getPages()+" "
        					+infoboxBook.getIsbn()+" "
        					+infoboxBook.getFollowed_by()+" "
        					+infoboxBook.getPreceded_by()
        					);
        			
        			infoboxBookList.add(infoboxBook);    			
        		}
        	}	
            bTitle = false;
            flag_book = false;     	 
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
