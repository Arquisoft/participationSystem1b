package hello.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hello.model.CitizenDB;
import hello.model.Suggestion;
import hello.model.VoteSuggestion;
import hello.model.key.VoteSuggestionKey;

public interface VoteSuggestionRepository  extends CrudRepository<VoteSuggestion, Long>{

	List<VoteSuggestion> findBySuggestion(Suggestion suggestion);

	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);

	VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey);

}
