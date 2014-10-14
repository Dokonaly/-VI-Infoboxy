package Parser;
import Enums.*;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
 
import org.xml.sax.SAXException;


import java.util.Scanner;
/**
 * @author Dokonaly
 *
 */
public class Parser {
	
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws FileNotFoundException, ParseException {
	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        
	        //handleri na parsovanie
	        HandlerCountry handler_country = new HandlerCountry();
	        HandlerSettlement handler_settlement = new HandlerSettlement();
	        HandlerBook handler_book = new HandlerBook();
	        HandlerPerson handler_person = new HandlerPerson();
	        
	        URL url = new URL("http://dumps.wikimedia.org/enwiki/latest/enwiki-latest-pages-articles1.xml-p000000010p000010000.bz2");
	        URLConnection connection = url.openConnection();

	        //oparsovanie pomocou kniznice SAX
	        /*
	        saxParser.parse(connection.getInputStream(), handler_country);
	        saxParser.parse(connection.getInputStream(), handler_settlement);
	        saxParser.parse(connection.getInputStream(), handler_book);
	        saxParser.parse(connection.getInputStream(), handler_person);
	        */
	        
	        String cesta = "data/ukazka.xml";
	        saxParser.parse(new File(cesta), handler_book);
	        saxParser.parse(new File(cesta), handler_person);
	        saxParser.parse(new File(cesta), handler_country);
	        saxParser.parse(new File(cesta), handler_settlement);
	       
	        
		    
	        
	        //zoznamy jednotlivych objektov
	        List<Infobox_country> InfoboxList = handler_country.getInfoboxList();
	        List<Infobox_settlement> InfoboxSettlementList = handler_settlement.getInfoboxList();
	        List<Infobox_book> InfoboxbookList = handler_book.getInfoboxList();
	        List<Infobox_person> InfoboxPersonList = handler_person.getInfoboxList();
	       
	        //serializacia
	        FileOutputStream fileOut = new FileOutputStream("Objects.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(InfoboxPersonList);
			out.writeObject(InfoboxList);
			out.writeObject(InfoboxSettlementList);
			out.writeObject(InfoboxbookList);
			out.close();
			fileOut.close();
			
			//vypocitanie statistik
	        Stats statisky = new Stats();
	        statisky.vypocitaj_statistiky(InfoboxbookList, InfoboxList, InfoboxSettlementList, InfoboxPersonList);
  
	         
	        
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	    }
	
	
	
}
