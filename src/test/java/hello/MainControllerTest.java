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
import java.util.HashSet;
import java.util.Set;

import model.*;

import model.key.CommentKey;
import model.key.VoteCommentKey;
import model.key.VoteSuggestionKey;

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
		long idSug1 = 12;

		long id1 = 123;
		long id2 = 321;

		Comment comNull = new Comment();
		assertNull(comNull.getId());
		assertNull(comNull.getCitizenDB());

		Date fecha = Calendar.getInstance().getTime();
		
	    CitizenDB user = new CitizenDB("nombre", "apellidos", "mail@mail.mail", fecha, "direccion", "naciolidad", "12345678D", "PARTICIPANT");
		Suggestion sug = new Suggestion(idSug1, "Sugerencia de prueba", user);

	    Comment comment = new Comment(id1, user, sug, "testeando");
	    Comment comment2 = new Comment(id2, user, sug, "testeando");

		VoteComment vcom = new VoteComment(comment, user);
		VoteComment vcom2 = new VoteComment(comment2, user);

		Set<VoteComment> voteComments = new HashSet<VoteComment>();
		voteComments.add(vcom);
		voteComments.add(vcom2);

		assertTrue(comment.getId().equals(id1));
		assertTrue(comment.getCitizenDB().equals(user));
		comment.setNumero_votos(2);
		assertEquals(comment.getNumero_votos(), 2);
		comment.setText("Texto de prueba");
		assertEquals(comment.getText(), "Texto de prueba");		
		assertFalse(comment.equals(comment2));
		assertTrue(comment2.equals(comment2));

		assertTrue(comment.getSuggestion().equals(sug));

		comment.setVoteComments(voteComments);
		assertTrue(comment.getVoteComments().equals(voteComments));
	}
	
	@Test
	public void testSuggestion() {
		long idSug1 = 12;
		long idSug2 = 21;

		long id1 = 123;
		long id2 = 321;

		CitizenDB user = new CitizenDB("nombre", "apellidos", "mail@mail.mail", Calendar.getInstance().getTime(), "direccion", "naciolidad", "12345678D", "PARTICIPANT");
		CitizenDB user2 = new CitizenDB("nombre2", "apellidos2", "mail2@mail2.mail", Calendar.getInstance().getTime(), "direccion2", "naciolidad2", "23456789D", "PARTICIPANT");

		Suggestion sug = new Suggestion(idSug1, "Sugerencia de prueba", user);
		Suggestion sug2 = new Suggestion(idSug2, "Sugerencia de prueba2", user);

		VoteSuggestion vsug = new VoteSuggestion(user, sug);
		VoteSuggestion vsug2 = new VoteSuggestion(user2, sug2);

		Comment comment = new Comment(id1, user, sug, "testeando");
		Comment comment2 = new Comment(id2, user, sug, "testeando");

		Set<Comment> comments = new HashSet<Comment>();
		comments.add(comment);
		comments.add(comment2);
		Set<VoteSuggestion> vSuggestions = new HashSet<VoteSuggestion>();
		vSuggestions.add(vsug);
		vSuggestions.add(vsug2);

		assertTrue(sug.getId().equals(idSug1));
		sug.setNum_votes(3);
		assertTrue(sug.getNum_votes() == 3);
		assertTrue(sug.getCitizenDB().equals(user));
		assertTrue(sug.getTitle().equals("Sugerencia de prueba"));

		sug.setCitizenDB(user2);
		assertFalse(sug.getCitizenDB().equals(user));

		sug.setTitle("Prueba de Sugerencia");
		assertFalse(sug.getTitle().equals("Sugerencia de prueba"));

		assertFalse(sug.equals(sug2));
		assertTrue(sug2.equals(sug2));
		assertFalse(sug.equals(new Suggestion()));

		sug.setVoteSuggestions(vSuggestions);
		assertTrue(sug.getVoteSuggestions().equals(vSuggestions));

		sug.setComments(comments);
		assertTrue(sug.getComments().equals(comments));

	}
	
	@Test
	public void testUser() {

		CitizenDB user1 = new CitizenDB();
		assertNull(user1.getName());
		assertNull(user1.getId());
		user1.setName("prueba1");
		user1.setSurname("codecov");
		user1.setMail("mailP@mail.com");
		user1.setAddress("arquitectura");
		user1.setNationality("español");
		user1.setBirthday(Calendar.getInstance().getTime());
		user1.setDNI("23456789E");
		user1.setType("PARTICIPANT");

		assertTrue(user1.getSurname().equals("codecov"));
		assertTrue(user1.getDNI().equals("23456789E"));
		assertTrue(user1.getNationality().equals("español"));

		String u1 = "[Nombre: " +user1.getName()+"] [Apellido: "+user1.getSurname()+"] [E-Mail: "+user1.getMail()
				+"] [Cumpleaños: "+user1.getBirthday()+"] [Direccion: "+user1.getAddress()
				+"] [Nacionalidad: "+user1.getNationality()+"] [DNI: "+user1.getDNI()+"]";

		assertEquals(user1.toString(), u1);
		
		CitizenDB user2 = new CitizenDB("nombre", "apellidos", "mail@mail.mail", Calendar.getInstance().getTime(), "direccion", "naciolidad", "12345678D", "PARTICIPANT");
		assertTrue(user2.getName().equals("nombre"));
		assertTrue(user2.getDNI().equals("12345678D"));
		assertTrue(user2.getType().equals("PARTICIPANT"));

		long c1 = 123;
		long c2 = 321;
		long idSug1 = 12;
		long idSug2 = 21;

		Suggestion sug = new Suggestion(idSug1, "Sugerencia de prueba", user1);
		Suggestion sug2 = new Suggestion(idSug2, "Sugerencia de prueba2", user1);

		VoteSuggestion vsug = new VoteSuggestion(user1, sug);
		VoteSuggestion vsug2 = new VoteSuggestion(user2, sug2);

		Comment comment = new Comment(c1, user1, sug, "testeando");
		Comment comment2 = new Comment(c2, user1, sug, "testeando");

		VoteComment vcom = new VoteComment(comment, user1);
		VoteComment vcom2 = new VoteComment(comment2, user1);

		Set<VoteComment> voteComments = new HashSet<VoteComment>();
		voteComments.add(vcom);
		voteComments.add(vcom2);

		Set<Comment> comments = new HashSet<Comment>();
		comments.add(comment);
		comments.add(comment2);

		Set<VoteSuggestion> vSuggestions = new HashSet<VoteSuggestion>();
		vSuggestions.add(vsug);
		vSuggestions.add(vsug2);

		user1.setComments(comments);
		assertTrue(user1.getComments().equals(comments));
		user1.setVotesSugerencias(vSuggestions);
		assertTrue(user1.getVotesSugerencias().equals(vSuggestions));
		user1.setVotesComments(voteComments);
		assertTrue(user1.getVotesComments().equals(voteComments));



	}

	@Test
	public void testVoteComment() {
		VoteComment vComNull = new VoteComment();
		assertNull(vComNull.getComment());
		assertNull(vComNull.getCitizenDB());

		long idSug = 12;

		long id1 = 123;
		long id2 = 321;
		long id3 = 234;

		Date fecha = Calendar.getInstance().getTime();
		
	    CitizenDB user = new CitizenDB("nombre", "apellidos", "mail@mail.mail", fecha, "direccion", "naciolidad", "12345678D", "PARTICIPANT");
	    CitizenDB user2 = new CitizenDB("nombre2", "apellidos2", "mail2@mail.mail", fecha, "direccion2", "naciolidad2", "12345678E", "PARTICIPANT");
	    Suggestion sug = new Suggestion(idSug, "Sugerencia de prueba", user);

	    Comment comment = new Comment(id1, user, sug, "testeando");
	    Comment comment2 = new Comment(id2, user, sug, "testeando");
	    
	    VoteComment vcom = new VoteComment(comment, user);
	    VoteComment vcom2 = new VoteComment(comment2, user2);

		assertTrue(vcom.getComment().getId().equals(id1));
		assertTrue(vcom.getComment().getCitizenDB().equals(user));
		assertTrue(vcom.getCitizenDB().equals(user));
		assertFalse(vcom.equals(vcom2));
		assertFalse(vcom.equals(null));
		assertTrue(vcom2.equals(vcom2));

		vcom.setId(id3);
		assertTrue(vcom.getId().equals(id3));
		
	}
	
	@Test
	public void testVoteSuggestion() {
		long idSug1 = 12;
		long idSug2 = 21;

		VoteSuggestion vSugNull = new VoteSuggestion();
		assertNull(vSugNull.getSuggestion());
		assertNull(vSugNull.getCitizenDB());
		
		Date fecha = Calendar.getInstance().getTime();
		
	    CitizenDB user = new CitizenDB("nombre", "apellidos", "mail@mail.mail", fecha, "direccion", "naciolidad", "12345678D", "PARTICIPANT");
	    CitizenDB user2 = new CitizenDB("nombre2", "apellidos2", "mail2@mail.mail", fecha, "direccion2", "naciolidad2", "12345678E", "PARTICIPANT");

	    Suggestion sug = new Suggestion(idSug1, "Sugerencia de prueba", user);
	    Suggestion sug2 = new Suggestion(idSug2, "Sugerencia de prueba2", user2);
	    
	    VoteSuggestion vsug = new VoteSuggestion(user, sug);
	    VoteSuggestion vsug2 = new VoteSuggestion(user2, sug2);

		assertTrue(vsug.getSuggestion().getId().equals(idSug1));
		assertTrue(vsug.getSuggestion().getCitizenDB().equals(user));
		assertTrue(vsug.getCitizenDB().equals(user));
		assertFalse(vsug.equals(vsug2));
		assertFalse(vsug.equals(null));
		assertTrue(vsug2.equals(vsug2));
		
	}

	@Test
	public void testCommentKey(){
		long idUser = 1;
		long idSuggestion = 12;
		long idUser2 = 2;
		long idSuggestion2 = 21;

		CommentKey ck1 = new CommentKey(idSuggestion, idUser);
		CommentKey ck2 = new CommentKey(idSuggestion, idUser2);
		CommentKey ck3 = new CommentKey(idSuggestion2, idUser);
		CommentKey ck4 = new CommentKey(idSuggestion2, idUser2);

		assertFalse(ck1.equals(ck2));
		assertFalse(ck1.equals(ck3));
		assertFalse(ck1.equals(ck4));
		assertFalse(ck2.equals(ck3));
		assertFalse(ck2.equals(ck4));
		assertFalse(ck3.equals(ck4));

		assertTrue(ck1.getCitizenDB().equals(idUser));
		ck1.setCitizenDB(idUser2);
		assertFalse(ck1.getCitizenDB().equals(idUser));
		assertTrue(ck1.getSuggestion().equals(idSuggestion));
		ck1.setSuggestion(idSuggestion2);
		assertFalse(ck1.getSuggestion().equals(idSuggestion));

	}

}