package Vzorka;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Help {
	//odstranenie prvej casti retazca
	public String ocisti_retazec(String ret, String Atribut){
		ret = ret.replace("|"+Atribut+" = ", "");
		ret = ret.replace("| "+Atribut+" = ", "");
		ret = ret.replace("|"+Atribut+"= ", "");
		ret = ret.replace("| "+Atribut+"= ", "");
		return ret;
	}
	
	 //Pouzitie regexu
	 public String PouziRegex(String regex, String vstup){
	    	Pattern pattern = Pattern.compile(regex);
	    	Matcher matcher = pattern.matcher(vstup);
	    	if (matcher.find())
	    	{
	    		return matcher.group(0);
	    	}
	    	return null;
	 }
	//odstranenie poslednej medzeri    
    public String posledna_medzera(String ret){
    	if (ret!=null && ret.length()>2){
    		String medzera= ret.substring(ret.length() - 1);
    		
    		if (medzera.contains(" ")){
    			ret = ret.substring(0,ret.length()-1);
    		}
    		}
		return ret;
    	
    }
}
