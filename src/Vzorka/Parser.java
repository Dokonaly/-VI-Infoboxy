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
	    PrintWriter out = new PrintWriter("vystup.txt");
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        
	        MyHandler handler = new MyHandler();
	        
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Google Drive\\FIIT rok 5\\VI\\Infobox projekt\\-VI-Infoboxy\\data\\ukazka.xml"), handler);
	        
	        List<Infobox> InfoboxList = handler.getInfoboxList();
	        
	        for(Infobox inf : InfoboxList){
	        	out.println(inf.getTitle()+" "
        		
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
	        	//System.out.println(inf.getTitle());
	        }
	        
	        out.close();
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	    }
}
