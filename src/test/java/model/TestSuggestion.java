package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestSuggestion {

	
	private Suggestion s1;
	private Suggestion s2;

	public TestSuggestion() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			Date date;
			Date date1;

			date = sdf.parse("01-01-1991");
			date1 = sdf.parse("02-02-1992");

			s1 = new Suggestion("titulo1",
					new CitizenDB("Nombre", "Apellidos", "mail", date, "address", "nacionality", "DNI", "Admin"));
			
			s2 = new Suggestion("titulo2",
					new CitizenDB("Nombre1","Apellidos1","mail1",date1,"address1","nacionality1","DNI1","Participant"));
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEquals() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			Date date = sdf.parse("01-01-1991");

			Suggestion copia1 = new Suggestion("titulo1",
					new CitizenDB("Nombre", "Apellidos", "mail", date, "address", "nacionality", "DNI", "Admin"));
			
			assert(s1.equals(copia1));

			assert (!s1.equals(s2));
			
			assert(!s1.equals(null));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
