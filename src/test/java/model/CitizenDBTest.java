package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import hello.model.CitizenDB;

/**
 * Clase de test para las pruebas unitarias
 * de la clase CitizenDB
 * 
 *
 */
public class CitizenDBTest {
	
	private final static String A = "admin";
	private final static String P = "participant";
	private final static String PO = "politician";
	
	private List<CitizenDB> citizens = new ArrayList<CitizenDB>();
	
	private void loadCitizensDB() throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		Date date =  sdf.parse("01-01-1991");
		Date date1 =  sdf.parse("02-02-1992");
		Date date2 =  sdf.parse("03-03-1993");
		Date date3 =  sdf.parse("04-04-1994");
		Date date4 =  sdf.parse("05-05-1995");
		Date date5 =  sdf.parse("06-06-1996");
		
		this.citizens.add(new CitizenDB("Nombre","Apellidos","mail",date,"address","nacionality","DNI",A));
		this.citizens.add(new CitizenDB("Nombre1","Apellidos1","mail1",date1,"address1","nacionality1","DNI1",P));
		this.citizens.add(new CitizenDB("Nombre2","Apellidos2","mail2",date2,"address2","nacionality2","DNI2",PO));
		this.citizens.add(new CitizenDB("Nombre3","Apellidos3","mail3",date3,"address3","nacionality3","DNI3",P));
		this.citizens.add(new CitizenDB("Nombre4","Apellidos4","mail4",date4,"address4","nacionality4","DNI4",P));
		this.citizens.add(new CitizenDB("Nombre5","Apellidos5","mail5",date5,"address5","nacionality5","DNI5",P));
	}
	
	public CitizenDBTest(){
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEquals(){
		assert(this.citizens.get(0).equals(new CitizenDB("Nombre","Apellidos","mail",null,"address","nacionality","DNI",A)));
		assert(this.citizens.get(1).equals(new CitizenDB("Nombre1","Apellidos1","mail1",null,"address1","nacionality1","DNI1",P)));
		assert(this.citizens.get(2).equals(new CitizenDB("Nombre2","Apellidos2","mail2",null,"address2","nacionality2","DNI2",PO)));
		assert(!this.citizens.get(0).equals(new CitizenDB("Nombre","Apellidos","mail",null,"address","nacionality","DNI2",A)));
		assert(!this.citizens.get(1).equals(new CitizenDB("Nombre1","Apellidos1","mail1",null,"address1","nacionality1","DNI",P)));
		assert(!this.citizens.get(2).equals(new CitizenDB("Nombre2","Apellidos2","mail2",null,"address2","nacionality2","DNI1",PO)));
	}
	
	@Test
	public void testHashCode(){
		
		assert(this.citizens.get(0).hashCode() == 67870);
		assert(this.citizens.get(1).hashCode() == 2103089);
		assert(this.citizens.get(2).hashCode() == 2103090);
		assert(this.citizens.get(3).hashCode() == 2103091);
		assert(this.citizens.get(4).hashCode() == 2103092);
		assert(this.citizens.get(5).hashCode() == 2103093);
	}
	
	@Test
	public void testToString(){
		assert(this.citizens.get(0).toString().contains("Nombre"));
		assert(this.citizens.get(1).toString().contains("Apellido"));
		assert(this.citizens.get(2).toString().contains("mail"));
		assert(this.citizens.get(3).toString().contains("Cumpleaños"));
		assert(this.citizens.get(4).toString().contains("Direccion"));
		assert(this.citizens.get(5).toString().contains("DNI"));
	}
	
	
	@Test
	public void testDNI(){
		assert(this.citizens.get(0).getDNI().compareTo("DNI") == 0);
		assert(this.citizens.get(1).getDNI().compareTo("DNI1") == 0);
		assert(this.citizens.get(2).getDNI().compareTo("DNI2") == 0);
		
		this.citizens.get(3).setDNI("1234A");
		assert(this.citizens.get(3).getDNI().compareTo("1234A") == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPassword(){
		this.citizens.get(0).setPassword("1234");
		assert(this.citizens.get(0).getPassword().compareTo("1234") == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNacionality(){
		assert(this.citizens.get(0).getNationality().compareTo("nacionality") == 0);
		assert(this.citizens.get(1).getNationality().compareTo("nacionality1") == 0);
		assert(this.citizens.get(2).getNationality().compareTo("nacionality2") == 0);	
		
		this.citizens.get(3).setNationality("Spanish");
		assert(this.citizens.get(3).getNationality().compareTo("Spanish") == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddress(){
		assert(this.citizens.get(0).getAddress().compareTo("address") == 0);
		assert(this.citizens.get(1).getAddress().compareTo("address1") == 0);
		assert(this.citizens.get(2).getAddress().compareTo("address2") == 0);
		
		this.citizens.get(3).setAddress("Oviedo");
		assert(this.citizens.get(3).getAddress().compareTo("Oviedo") == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBirthday() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		
		assert(this.citizens.get(0).getBirthday().compareTo(sdf.parse("01-01-1991")) == 0);
		assert(this.citizens.get(1).getBirthday().compareTo(sdf.parse("02-02-1992")) == 0);
		assert(this.citizens.get(2).getBirthday().compareTo(sdf.parse("03-03-1993")) == 0);
		
		this.citizens.get(3).setBirthday(sdf.parse("18-01-1983"));
		assert(this.citizens.get(3).getBirthday().compareTo(sdf.parse("18-01-1983")) == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMail(){
		assert(this.citizens.get(0).getMail().compareTo("mail") == 0);
		assert(this.citizens.get(1).getMail().compareTo("mail1") == 0);
		assert(this.citizens.get(2).getMail().compareTo("mail2") == 0);
		
		this.citizens.get(3).setMail("uo173944@uniovi.es");
		assert(this.citizens.get(3).getMail().compareTo("uo173944@uniovi.es") == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSurname(){
		assert(this.citizens.get(0).getSurname().compareTo("Apellidos") == 0);
		assert(this.citizens.get(1).getSurname().compareTo("Apellidos1") == 0);
		assert(this.citizens.get(2).getSurname().compareTo("Apellidos2") == 0);
		
		this.citizens.get(3).setSurname("garcia");
		assert(this.citizens.get(3).getSurname().compareTo("garcia") == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testName(){
		assert(this.citizens.get(0).getName().compareTo("Nombre") == 0);
		assert(this.citizens.get(1).getName().compareTo("Nombre1") == 0);
		assert(this.citizens.get(2).getName().compareTo("Nombre2") == 0);
		
		this.citizens.get(3).setName("David");
		assert(this.citizens.get(3).getName().compareTo("David") == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testType(){
		assert(this.citizens.get(0).getType().compareTo(A) == 0);
		assert(this.citizens.get(1).getType().compareTo(P) == 0);
		assert(this.citizens.get(2).getType().compareTo(PO) == 0);
		
		this.citizens.get(2).setType(A);
		assert(this.citizens.get(2).getType().compareTo(A) == 0);
		
		this.citizens.removeAll(citizens);
		try {
			this.loadCitizensDB();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
