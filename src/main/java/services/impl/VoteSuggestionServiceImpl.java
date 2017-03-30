package services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.CitizenDB;
import model.Suggestion;
import model.VoteSuggestion;
import model.key.VoteSuggestionKey;
import repository.VoteSuggestionRepository;
import services.VoteSuggestionService;

public class VoteSuggestionServiceImpl implements VoteSuggestionService{

	@Autowired
	private VoteSuggestionRepository voteSuggestionServiceImpl;
	
	@Override
	public List<VoteSuggestion> findBySuggestion(Suggestion suggestion) {
		return voteSuggestionServiceImpl.findBySuggestion(suggestion);
	}

	@Override
	public List<VoteSuggestion> findByCitizenDB(CitizenDB citizen) {
		return voteSuggestionServiceImpl.findByCitizenDB(citizen);
	}

	@Override
	public VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey) {
		return voteSuggestionServiceImpl.findByVoteCommentKey(voteSuggestionKey);
	}

	@Override
	public VoteSuggestion createVoteSuggestion(VoteSuggestion voteSuggestion) {
		return voteSuggestionServiceImpl.save(voteSuggestion);
	}

	@Override
	public void deleteVoteSuggestion(VoteSuggestion voteSuggestion) {
		voteSuggestionServiceImpl.delete(voteSuggestion);
		
	}
}
