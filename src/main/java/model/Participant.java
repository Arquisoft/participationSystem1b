package model;

import java.util.List;

public class Participant extends AbstractParticipant{
	
	private List<Suggestion> voted_suggestions;
	private List<Suggestion> commented_suggestions;
	
	public Participant(String name,String surname,String mail,String login,String password,
			List<Suggestion> voted_suggestions,List<Suggestion> commented_suggestions){
		super(name,surname,mail,login,password);
		this.voted_suggestions = voted_suggestions;
		this.commented_suggestions = commented_suggestions;
	}
	
	public Suggestion getSuggestion(int id){
		// return db.getQuery(id);
		return null;
	}

	public void vote_query(int id_query){
		// bd insertar un voto en la sugerencia
		// this.voted_suggestions.add(bd.getsuggestion)....
	}
	
	public void makeSuggestion(Suggestion suggestion){
		// bd. insertar sugerencia...
		// quizás estos métodos tendría más sentido hacerlos booleans para saber si se insertó o no correctamente...
	}
	
	public void commentSuggestion(int id){
		// bd insertar un voto en la sugerencia
		// this.commented_suggestions.add(bd.getsuggestion)....
	}
	
	public List<Suggestion> getVoted_suggestion(){
		return this.voted_suggestions;
	}
	
	public List<Suggestion> getCommented_suggestion(){
		return this.commented_suggestions;
	}
	
	@Override
	public void saveParticipant() {
		// TODO Auto-generated method stub
	}
}
