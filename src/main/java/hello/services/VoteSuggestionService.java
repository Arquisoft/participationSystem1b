package hello.services;

import java.util.List;

import hello.model.CitizenDB;
import hello.model.Suggestion;
import hello.model.VoteSuggestion;
import hello.model.key.VoteSuggestionKey;

public interface VoteSuggestionService {

	List<VoteSuggestion> findBySuggestion (Suggestion suggestion);
	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);
	VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey);
	
	VoteSuggestion createVoteSuggestion(VoteSuggestion voteSuggestion);
	void deleteVoteSuggestion (VoteSuggestion voteSuggestion);
}
