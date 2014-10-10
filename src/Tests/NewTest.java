package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Vzorka.HandlerCountry;
import Vzorka.Infobox_book;

public class NewTest {
	
	String retazec = "{{Infobox country" +
"|conventional_long_name = Union of the Comoros"+ 
"|common_name = Comoros";
	
	Infobox_book book = new Infobox_book();
	HandlerCountry handlerC = new HandlerCountry();
		
  @Test
  public void testOparsuj_country() {
	  
	  retazec = retazec.replaceAll("(\r\n|\n)", " "); 
	  retazec = retazec.trim().replaceAll(" +", " "); 
  	
  	  String text = handlerC.PouziRegex("\\{\\{Infobox country \\s*(.*)", retazec);
	  boolean flag = handlerC.oparsujCountry(true, text);
	  Assert.assertEquals(flag, false);
  }
}
