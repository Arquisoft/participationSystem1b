package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import hello.model.CitizenDB;
import hello.model.Suggestion;
import hello.model.VoteSuggestion;

/**
 * 
 * Clase de test para la prueba 
 * unitaria de la clase
 * VoteSuggestion
 *
 */
public class TestVoteSuggestion {
	
	private VoteSuggestion vs1;
	private VoteSuggestion vs2;
	private CitizenDB c1;
	private CitizenDB c2;
	private Suggestion s1;
	private Suggestion s2;
	
	
	public TestVoteSuggestion(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			Date date;
			Date date1;
			
			date = sdf.parse("01-01-1991");
			date1 =  sdf.parse("02-02-1992");
			
			c1 = new CitizenDB("Nombre","Apellidos","mail",date,"address","nacionality","DNI","Admin");
			c2 = new CitizenDB("Nombre1","Apellidos1","mail1",date1,"address1","nacionality1","DNI1","Participant");
			
			s1 = new Suggestion("Titulo",c1);
			s2 = new Suggestion("Titulo2",c2);
			
			vs1 = new VoteSuggestion(c1,s1);
			
			vs2 = new VoteSuggestion(c2,s2);
					
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEquals(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			Date date;
			Date date1;

			date = sdf.parse("01-01-1991");
			date1 =  sdf.parse("02-02-1992");
			
			c1 = new CitizenDB("Nombre","Apellidos","mail",date,"address","nacionality","DNI","Admin");
			
			assert(vs1.equals(new VoteSuggestion(c1,new Suggestion("Titulo",new CitizenDB("Nombre","Apellidos","mail",date,"address","nacionality","DNI","Admin")))));
			
			assert(vs2.equals(vs2 = new VoteSuggestion(new CitizenDB("Nombre1","Apellidos1","mail1",date1,"address1","nacionality1","DNI1","Participant"),
					new Suggestion("Titulo2",new CitizenDB("Nombre1","Apellidos1","mail1",date1,"address1","nacionality1","DNI1","Participant")))));
			
			assert(!vs1.equals(vs2));
			
			assert(!vs1.equals(null));
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testHascode(){
		assert(vs1.hashCode() == 31 * 1 + ((vs1.getCitizenDB() == null) ? 0 : vs1.getCitizenDB().hashCode()));
		
		assert(vs2.hashCode() == 31 * 1 + ((vs2.getCitizenDB() == null) ? 0 : vs2.getCitizenDB().hashCode()));
		
		assert(vs1.hashCode() != vs2.hashCode());
	}
	
}


