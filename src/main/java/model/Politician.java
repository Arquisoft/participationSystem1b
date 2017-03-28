package model;

import java.util.ArrayList;
import java.util.List;

public class Politician extends AbstractParticipant{

	/*
	 * La clase político se añade de momento al modelo porque nos suena que 
	 * Aquilino lo mencionó durante la presentación de la asignatura.
	 * No obstante yo creo que sería bueno preguntarle de nuevo a Aquilino 
	 * sobre la necesidad de esta clase, ya que de momento la clase no tiene 
	 * una funcionalidad añadida a la del partipante normal (quizás el hecho de ver las consultas
	 * más votadas o algo similar...)
	 */
	
	private final static int NUM_SUGGESTIONS = 5;
	
	public Politician(String name, String surname, String mail, String login, String password) {
		super(name, surname, mail, login, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveParticipant() {
		// TODO Auto-generated method stub
	}

	public List<Suggestion> getMoreSupportedSuggestions(){
		List<Suggestion> list = new ArrayList<Suggestion>();  
		//podríamos insertar en la lista el número de sugerencias indicado en la variable global NUM_SUGGESTIONS
		return null;
	}
}
