package Vzorka;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerSettlement  extends DefaultHandler {
	
	private List<Infobox_settlement> infobox_settlementList = null;
	private Infobox_settlement infobox_settlement = null;
	private StringBuffer sb;
	
	public List<Infobox_settlement> getInfoboxList() {
        return infobox_settlementList;
    }

	boolean bTitle = false;
	
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
    	sb = new StringBuffer();
        if (qName.equalsIgnoreCase("Page")) {                
        	infobox_settlement = new Infobox_settlement();
            if (infobox_settlementList == null) {
            	infobox_settlementList = new ArrayList<>();
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
    
    public boolean oparsujSettlement(boolean flag, String vysledok){
    	flag = false;
    	String vystup = PouziRegex("\\| ?official_name = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|official_name = ", "");
    		vystup = vystup.replace("| official_name = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setOfficial_name(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?nickname = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|nickname = ", "");
    		vystup = vystup.replace("| nickname = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setNickname(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?map_caption = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|map_caption = ", "");
    		vystup = vystup.replace("| map_caption = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setMap_caption(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?coordinates_region = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|coordinates_region = ", "");
    		vystup = vystup.replace("| coordinates_region = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z:.,?! +-]","");
    	    infobox_settlement.setCoordinates_region(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?leader_title = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|leader_title = ", "");
    		vystup = vystup.replace("| leader_title = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setLeader_title(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?unit_pref = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|unit_pref = ", "");
    		vystup = vystup.replace("| unit_pref = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setUnit_pref(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?area_total_km2 = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|area_total_km2 = ", "");
    		vystup = vystup.replace("| area_total_km2 = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setArea_total_km2(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?area_land_km2 = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|area_land_km2 = ", "");
    		vystup = vystup.replace("| area_land_km2 = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setArea_land_km2(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?population_total = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{|'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|population_total = ", "");
    		vystup = vystup.replace("| population_total = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-]","");
    	    infobox_settlement.setPopulation_total(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?population_density_km2 = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{|'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|population_density_km2 = ", "");
    		vystup = vystup.replace("| population_density_km2 = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.,:?! +-]","");
    	    infobox_settlement.setPopulation_density_km2(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?timezone = [A-Za-z0-9 _ =*.:?!()+-<>\\[#@\\{}|'` $%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|timezone = ", "");
    		vystup = vystup.replace("| timezone = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-|]","");
    	    infobox_settlement.setTimezone(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?website = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|website = ", "");
    		vystup = vystup.replace("| website = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-|]","");
    	    infobox_settlement.setWebsite(vystup);
    	    flag = true;
    	}
    	
    	vystup = PouziRegex("\\| ?postal_code = [A-Za-z0-9 _ =*.:?!()+-<>\\]\\[#@\\{}'`$%^&;<>,ֹציז]+", vysledok);  	
    	if (vystup != null){
    		vystup = vystup.replace("|postal_code = ", "");
    		vystup = vystup.replace("| postal_code = ", "");
    		vystup = vystup.replaceAll("[^0-9a-zA-Z.:,?! +-|]","");
    	    infobox_settlement.setPostal_code(vystup);
    	    flag = true;
    	}
    	
    	return flag;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	boolean flag_settlement = false;
    	
        if (bTitle) {
          
        	String vysledok = sb.toString();
        	vysledok = vysledok.replaceAll("(\r\n|\n)", " "); //zarovnanie do jedneho riadku
        	vysledok = vysledok.trim().replaceAll(" +", " "); //odstranenie nepotrebnych medzier
        	
        	String text = PouziRegex("\\{\\{Infobox settlement \\s*(.*)", vysledok);
        	
        	if (text !=null){
        		//System.out.println(text);
        		flag_settlement  = oparsujSettlement(flag_settlement, text);
        	}
        	if (flag_settlement == true){
        		if ( infobox_settlement.getOfficial_name()!= null ||  infobox_settlement.getPopulation_total() != null){
        			System.out.println(infobox_settlement.getOfficial_name()+" "
        					+infobox_settlement.getNickname() +" "
        					+infobox_settlement.getMap_caption()+" "
        					+infobox_settlement.getCoordinates_region()+" "
        					+infobox_settlement.getLeader_title()+" "
        					+infobox_settlement.getLeader_title()+" "
        					+infobox_settlement.getUnit_pref()+" "
        					+infobox_settlement.getArea_total_km2()+" "
        					+infobox_settlement.getArea_land_km2()+" "
        					+infobox_settlement.getPopulation_total()+" "
        					+infobox_settlement.getPopulation_density_km2()+" "
        					+infobox_settlement.getTimezone()+" "
        					+infobox_settlement.getWebsite()+" "
        					+infobox_settlement.getPostal_code()
        					);
        			infobox_settlementList.add(infobox_settlement);
            	       			
        		}
        	}	
            bTitle = false;
            flag_settlement = false;     	 
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
