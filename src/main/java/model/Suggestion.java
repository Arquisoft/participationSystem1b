package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Suggestion {
	
	private Participant creator;
	private int suggestion_id;
	private String suggestion_name;
	private List<Comment> comments;
	private int num_votes; // quizás estaría bien que la consulta cargara su número de votos de la BD en primera instancia
						   // y de cara al rendimiento los mantuviera en esta variable (hablarlo)
	private Date suggestion_date;
	private String content;
	
	public Suggestion(Participant creator,int suggestion_id,String suggestion_name,List<Comment> comments,
			int num_votes,Date suggestion_date,String content){
		this.creator = creator;
		this.suggestion_id = suggestion_id;
		this.suggestion_name = suggestion_name;
		this.comments = comments;
		this.num_votes = num_votes;
		this.suggestion_date = suggestion_date;
		this.setContent(content);
	}
	
	public Suggestion(){
		this.num_votes = 0;
		comments = new ArrayList<Comment>();
		this.suggestion_date = new Date();
	}

	public String getSuggestion_name() {
		return suggestion_name;
	}

	public void setSuggestion_name(String suggestion_name) {
		this.suggestion_name = suggestion_name;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getNum_votes() {
		return num_votes;
	}

	public void setNum_votes(int num_votes) {
		this.num_votes = num_votes;
	}

	public Date getSuggestion_date() {
		return suggestion_date;
	}

	public void setSuggestion_date(Date suggestion_date) {
		this.suggestion_date = suggestion_date;
	}
	
	public int getSuggestion_id() {
		return suggestion_id;
	}

	public void setSuggestion_id(int suggestion_id) {
		this.suggestion_id = suggestion_id;
	}

	public void saveSuggestion(){
		//bd.insert_suggestion()...
	}

	public Participant getCreator() {
		return creator;
	}

	public void setCreator(Participant creator) {
		this.creator = creator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
