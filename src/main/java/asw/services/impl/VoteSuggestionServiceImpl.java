package asw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.model.CitizenDB;
import asw.model.Suggestion;
import asw.model.VoteSuggestion;
import asw.model.key.VoteSuggestionKey;
import asw.repository.VoteSuggestionRepository;
import asw.services.VoteSuggestionService;

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
