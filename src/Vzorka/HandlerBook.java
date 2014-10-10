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
 
    public String PouziRegex(String regex, String vstup){
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(vstup);
    	if (matcher.find())
    	{
    		return matcher.group(0);
    	}
    	return null;
    }
    
    public boolean oparsujBook(String vysledok){
    	boolean flag = false;
    	
    	String vystup = PouziRegex("\\| ?name = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|name = ", "");
    		vystup = vystup.replace("| name = ", "");
    	    infoboxBook.setName(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?translator = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|translator = ", "");
    		vystup = vystup.replace("| translator = ", "");
    		infoboxBook.setTranslator(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?image = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|image = ", "");
    		vystup = vystup.replace("| image = ", "");
    		infoboxBook.setImage(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?caption = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|caption = ", "");
    		vystup = vystup.replace("| caption = ", "");
    		infoboxBook.setCaption(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?author = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|author = ", "");
    		vystup = vystup.replace("| author = ", "");
    		infoboxBook.setAuthor(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?country = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|country = ", "");
    		vystup = vystup.replace("| country = ", "");
    		infoboxBook.setCountry(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?language = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|language = ", "");
    		vystup = vystup.replace("| language = ", "");
    		infoboxBook.setLanguage(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?subject = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|subject = ", "");
    		vystup = vystup.replace("| subject = ", "");
    		infoboxBook.setSubject(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?genre = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|genre = ", "");
    		vystup = vystup.replace("| genre = ", "");
    		infoboxBook.setGenre(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?published = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|published = ", "");
    		vystup = vystup.replace("| published = ", "");
    		infoboxBook.setPublished(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?media_type = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|media_type = ", "");
    		vystup = vystup.replace("| media_type = ", "");
    		infoboxBook.setMedia_type(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?pages = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|pages = ", "");
    		vystup = vystup.replace("| pages = ", "");
    		infoboxBook.setPages(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?isbn = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|isbn = ", "");
    		vystup = vystup.replace("| isbn = ", "");
    		infoboxBook.setIsbn(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?preceded_by = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|preceded_by = ", "");
    		vystup = vystup.replace("| preceded_by = ", "");
    		infoboxBook.setPreceded_by(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?followed_by = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|followed_by = ", "");
    		vystup = vystup.replace("| followed_by = ", "");
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
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); //zarovnanie do jedneho riadku
        	vysledok = vysledok.trim().replaceAll(" +", " "); //odstranenie nepotrebnych medzier
        	
        	String text = PouziRegex("\\{\\{Infobox book \\s*(.*)", vysledok);
        
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
