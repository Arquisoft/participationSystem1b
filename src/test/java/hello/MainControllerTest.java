package hello;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Date;
import java.util.Calendar;

import model.CitizenDB;
import model.Comment;
import model.Suggestion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import controllers.Application;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class MainControllerTest {

    @Value("${local.server.port}")
    private int port;

    private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void getLanding() throws Exception {
		String userURI = base.toString() + "/index";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), containsString("Usuario"));
		assertThat(response.getBody(), containsString("Contraseña"));
	}
	
	@Test
	public void testComment() {
		Comment comNull = new Comment();
		assertNull(comNull.getId());
		assertNull(comNull.getCitizenDB());
		
		Date fecha = Calendar.getInstance().getTime();
		
	    CitizenDB user = new CitizenDB("nombre", "apellidos", "mail@mail.mail", fecha, "direccion", "naciolidad", "12345678D", "PARTICIPANT");
		Suggestion sug = new Suggestion((long) 12, "Sugerencia de prueba", user);

	    Comment comment = new Comment((long)123, user, sug, "testeando");
	    Comment comment2 = new Comment((long)321, user, sug, "testeando");

		assertTrue(comment.getId().equals((long) 123));
		assertTrue(comment.getCitizenDB().equals(user));
		comment.setNumero_votos(2);
		assertEquals(comment.getNumero_votos(), 2);
		comment.setText("Texto de prueba");
		assertEquals(comment.getText(), "Texto de prueba");		
		assertFalse(comment.equals(comment2));
		
	}
	
	@Test
	public void testSuggestion() {
		CitizenDB user = new CitizenDB("nombre", "apellidos", "mail@mail.mail", Calendar.getInstance().getTime(), "direccion", "naciolidad", "12345678D", "PARTICIPANT");
		Suggestion sug = new Suggestion((long) 12, "Sugerencia de prueba", user);
		
		assertTrue(sug.getId().equals((long) 12));
		sug.setNum_votes(3);
		assertTrue(sug.getNum_votes() == 3);
		assertTrue(sug.getCitizenDB().equals(user));
		assertTrue(sug.getTitle().equals("Sugerencia de prueba"));
	}
	
	@Test
	public void testUser() {
		CitizenDB user1 = new CitizenDB();
		assertNull(user1.getName());
		assertNull(user1.getId());
		
		CitizenDB user2 = new CitizenDB("nombre", "apellidos", "mail@mail.mail", Calendar.getInstance().getTime(), "direccion", "naciolidad", "12345678D", "PARTICIPANT");
		assertTrue(user2.getName().equals("nombre"));
		assertTrue(user2.getDNI().equals("12345678D"));
		assertTrue(user2.getType().equals("PARTICIPANT"));
	}

	
}