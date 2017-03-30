package services;

import java.util.List;

import model.CitizenDB;
import model.Suggestion;
import model.VoteSuggestion;
import model.key.VoteSuggestionKey;

public interface VoteSuggestionService {

	List<VoteSuggestion> findBySuggestion (Suggestion suggestion);
	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);
	VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey);
	
	VoteSuggestion createVoteSuggestion(VoteSuggestion voteSuggestion);
	void deleteVoteSuggestion (VoteSuggestion voteSuggestion);
}
