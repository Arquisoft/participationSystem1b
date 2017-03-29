package hello.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hello.model.key.VoteKey;



@Entity
@IdClass(VoteKey.class)
@Table(name="Voto")
public class Vote {
	
	@Id
	@ManyToOne
	private Comment comment;
	@Id
	@ManyToOne
	private Suggestion suggestion;
	
	public Vote(Comment comment , Suggestion suggestion){
		this.comment = comment;
		this.suggestion = suggestion;
	}

	public Comment getComment() {
		return comment;
	}

	void _setComment(Comment comment) {
		this.comment = comment;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	void _setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
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
		Vote other = (Vote) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (suggestion == null) {
			if (other.suggestion != null)
				return false;
		} else if (!suggestion.equals(other.suggestion))
			return false;
		return true;
	}
	
	

}
