package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.key.VoteSuggestionKey;



@Entity
@IdClass(VoteSuggestionKey.class)
@Table(name="VotoSugerencia")
public class VoteSuggestion {
	
	@Id
	@ManyToOne
	private CitizenDB citizenDB;
	@Id
	@ManyToOne
	private Suggestion suggestion;
	
	VoteSuggestion() {
	}
	
	public VoteSuggestion(CitizenDB citizenDB , Suggestion suggestion){
		Association.votarSugerencia.link(citizenDB, suggestion, this);
	}

	public CitizenDB getCitizenDB() {
		return citizenDB;
	}

	void _setCitizenDB(CitizenDB citizenDB) {
		this.citizenDB = citizenDB;
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
		VoteSuggestion other = (VoteSuggestion) obj;
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
}
