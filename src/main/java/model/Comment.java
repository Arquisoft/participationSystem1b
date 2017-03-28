package model;

import java.util.Date;

public class Comment {
	private int id;
	private Participant participant;
	private Date date;
	
	
	public Comment(int id,Participant participant,Date date){
		this.id = id;
		this.participant = participant;
		this.date = date;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Participant getParticipant() {
		return participant;
	}


	public void setParticipant(Participant participant) {
		this.participant = participant;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	public void saveParticipant(){
		// algo del tipo db.insert(.....);
	}
}
