package model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.key.CommentKey;

@Entity
@IdClass(CommentKey.class)
@Table(name = "Comentario")
public class Comment {
	
	@Id
	@ManyToOne
	private CitizenDB citizenDB;
	@Id
	@ManyToOne
	private Suggestion suggestion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@OneToMany(mappedBy = "comment")
	private Set<VoteComment> voteComments;
	
	Comment(){
		
	}
	
	public Comment(CitizenDB citizenDB , Suggestion suggestion){
		Association.Comentar.link(citizenDB, suggestion, this);
		this.date = Calendar.getInstance().getTime();
	}

	
	
	public Set<VoteComment> getVoteComments() {
		return new HashSet<>(voteComments);
	}
	
	Set<VoteComment> _getVoteComments() {
		return voteComments;
	}

	public void setVoteComments(Set<VoteComment> voteComments) {
		this.voteComments = voteComments;
	}

	public CitizenDB getCitizenDB() {
		return citizenDB;
	}
	
	void _setCitizenDB(CitizenDB citizenDB) {
		this.citizenDB = citizenDB ;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}
	
	void _setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizenDB == null) ? 0 : citizenDB.hashCode());
		result = prime * result + ((suggestion == null) ? 0 : suggestion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (citizenDB == null) {
			if (other.citizenDB != null)
				return false;
		} else if (!citizenDB.equals(other.citizenDB))
			return false;
		if (suggestion == null) {
			if (other.suggestion != null)
				return false;
		} else if (!suggestion.equals(other.suggestion))
			return false;
		return true;
	}

	public void saveParticipant(){
		// algo del tipo db.insert(.....);
	}
}
