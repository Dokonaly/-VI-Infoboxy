package Vzorka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
 
import org.xml.sax.SAXException;

public class Parser {
	public static void main(String[] args) throws FileNotFoundException {
	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    PrintWriter out_country = new PrintWriter("Out_Country.txt");
	    PrintWriter out_settlement = new PrintWriter("Out_Settlement.txt");
	    PrintWriter out_book = new PrintWriter("Out_Book.txt");
	    PrintWriter out_person = new PrintWriter("Out_Person.txt");
	    
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	       
	        HandlerCountry handler_country = new HandlerCountry();
	        HandlerSettlement handler_settlement = new HandlerSettlement();
	        HandlerBook handler_book = new HandlerBook();
	        HandlerPerson handler_person = new HandlerPerson();
	        
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Google Drive\\FIIT rok 5\\VI\\Infobox projekt\\-VI-Infoboxy\\data\\ukazka.xml"), handler_country);
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Google Drive\\FIIT rok 5\\VI\\Infobox projekt\\-VI-Infoboxy\\data\\ukazka.xml"), handler_settlement);
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Google Drive\\FIIT rok 5\\VI\\Infobox projekt\\-VI-Infoboxy\\data\\ukazka.xml"), handler_book);
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Google Drive\\FIIT rok 5\\VI\\Infobox projekt\\-VI-Infoboxy\\data\\ukazka.xml"), handler_person);
		    
	        List<Infobox_country> InfoboxList = handler_country.getInfoboxList();
	        List<Infobox_settlement> InfoboxSettlementList = handler_settlement.getInfoboxList();
	        List<Infobox_book> InfoboxbookList = handler_book.getInfoboxList();
	        List<Infobox_person> InfoboxPersonList = handler_person.getInfoboxList();
	        
	        for(Infobox_person person : InfoboxPersonList){
	        	out_person.println(person.getName()+" "
    					+person.getImage()+" "
    					+person.getImage_size()+" "
    					+person.getBirth_date()+" "
    					+person.getBirth_place()+" "
    					+person.getDeath_date()+" "
    					+person.getDeath_place()+" "
    					+person.getOccupation()+" "
	        			);
	        }
	        
	        for(Infobox_book book : InfoboxbookList){
	        	out_book.println(book.getName()+" "
    					+book.getTranslator()+" "
    					+book.getImage()+" "
    					+book.getCaption()+" "
    					+book.getAuthor()+" "
    					+book.getCountry()+" "
    					+book.getLanguage()+" "
    					+book.getSubject()+" "
    					+book.getGenre()+" "
    					+book.getPublished()+" "
    					+book.getMedia_type()+" "
    					+book.getPages()+" "
    					+book.getIsbn()+" "
    					+book.getFollowed_by()+" "
    					+book.getPreceded_by()
	        			);
	        }
	        
	        for(Infobox_settlement settlement : InfoboxSettlementList){
	        	out_settlement.println(settlement.getOfficial_name()+" "
    					+settlement.getNickname() +" "
    					+settlement.getMap_caption()+" "
    					+settlement.getCoordinates_region()+" "
    					+settlement.getLeader_title()+" "
    					+settlement.getLeader_title()+" "
    					+settlement.getUnit_pref()+" "
    					+settlement.getArea_total_km2()+" "
    					+settlement.getArea_land_km2()+" "
    					+settlement.getPopulation_total()+" "
    					+settlement.getPopulation_density_km2()+" "
    					+settlement.getTimezone()+" "
    					+settlement.getWebsite()+" "
    					+settlement.getPostal_code()
	        			);
	        }
	        
	        for(Infobox_country inf : InfoboxList){
	        	out_country.println(inf.getTitle()+" "
        		
        					+inf.getCommon_name() +" "
        					+inf.getImage_flag()+" "
        					+inf.getImage_coat()+" "
        					+inf.getCapital()+" "
        					+inf.getOfficial_religion()+" "
        					+inf.getOfficial_languages()+" "
        					+inf.getGovernment_type()+" "
        					+inf.getArea_km2()+" "
        					+inf.getArea_sq_mi()+" "
        					+inf.getCurrency()+" "
        					+inf.getCurrency_code()+" "
        					+inf.getPopulation_estimate()+" "
        					+inf.getPopulation_estimate_rank()
        					);
	        }
	        out_person.close();
	        out_book.close();
	        out_settlement.close(); 
	        out_country.close();
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	    }
}
