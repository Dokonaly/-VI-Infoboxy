package Vzorka;

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
	public static void main(String[] args) throws FileNotFoundException, ParseException {
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
	        
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Desktop\\data\\ukazka.xml"), handler_country);
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Desktop\\data\\ukazka.xml"), handler_settlement);
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Desktop\\data\\ukazka.xml"), handler_book);
	        saxParser.parse(new File("C:\\Users\\Dokonaly\\Desktop\\data\\ukazka.xml"), handler_person);
		    
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
	        
	        Stats statisky = new Stats();
	        statisky.vypocitaj_statistiky(InfoboxbookList, InfoboxList, InfoboxSettlementList, InfoboxPersonList);
	        
			StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	        Directory index = new RAMDirectory();  
	        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	        IndexWriter w = new IndexWriter(index, config);
	        
	        for(Infobox_book book : InfoboxbookList){
	        addBook(w, book.getName(), book.getTranslator(), book.getImage(), book.getCaption(), book.getAuthor(), 
	        		book.getCountry(),book.getLanguage(), book.getSubject(), book.getGenre(), book.getPublished(),
	        		book.getMedia_type(), book.getPages(), book.getIsbn(), book.getFollowed_by() , book.getPreceded_by());
	        }
	        
	        /*
	        String querystr = args.length > 0 ? args[0] : "Blade";
	        Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse(querystr);
	        
	        int hitsPerPage = 10;
	        IndexReader reader = DirectoryReader.open(index);
	        IndexSearcher searcher = new IndexSearcher(reader);
	        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
	        searcher.search(q, collector);
	        ScoreDoc[] hits = collector.topDocs().scoreDocs;
	        
	        System.out.println("Found " + hits.length + " hits.");
	        for(int i=0;i<hits.length;++i) {
	          int docId = hits[i].doc;
	          Document d = searcher.doc(docId);
	          System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
	        }

	        reader.close();*/
	        
	        out_person.close();
	        out_book.close();
	        out_settlement.close(); 
	        out_country.close();
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	    }
	
	private static void addBook(IndexWriter w, String title, String translator, String image,
			String captation, String author, String country, String language,
			String subject, String genre, String published, String media_type,
		    String pages,String isbn, String get_followed_by, String get_preceded_by) throws IOException {
		  Document doc = new Document();
		  doc.add(new TextField("title", title, Field.Store.YES));
		  doc.add(new StringField("translator", translator, Field.Store.YES));
		  doc.add(new StringField("image", image, Field.Store.YES));
		  doc.add(new StringField("captation", captation, Field.Store.YES));
		  doc.add(new StringField("author", author, Field.Store.YES));
		  doc.add(new StringField("country", country, Field.Store.YES));
		  doc.add(new StringField("language", language, Field.Store.YES));
		  doc.add(new StringField("subject", subject, Field.Store.YES));
		  doc.add(new StringField("genre", genre, Field.Store.YES));
		  doc.add(new StringField("published", published, Field.Store.YES));
		  doc.add(new StringField("media_type", media_type, Field.Store.YES));
		  doc.add(new StringField("pages", pages, Field.Store.YES));
		  doc.add(new StringField("isbn", isbn, Field.Store.YES));
		  doc.add(new StringField("get_followed_by", get_followed_by, Field.Store.YES));
		  doc.add(new StringField("get_preceded_by", get_preceded_by, Field.Store.YES));
		  w.addDocument(doc);
		}
}
