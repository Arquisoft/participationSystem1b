package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.CitizenDB;
import model.Suggestion;
import model.VoteSuggestion;
import model.key.VoteSuggestionKey;

public interface VoteSuggestionRepository  extends CrudRepository<VoteSuggestion, Long>{

	List<VoteSuggestion> findBySuggestion(Suggestion suggestion);

	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);

	VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey);

}
