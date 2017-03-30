package services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.CitizenDB;
import model.Suggestion;
import repository.SuggestionRepository;
import services.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService{

	@Autowired
	private SuggestionRepository suggestionRepository;
	@Override
	public List<Suggestion> findByCitizenDB(CitizenDB citizenDB) {
		return suggestionRepository.findByCitizenDB(citizenDB);
	}

	@Override
	public Suggestion findByTitle(String title) {
		return suggestionRepository.findByTitle(title);
	}

	@Override
	public List<Suggestion> findAll() {
		return (List<Suggestion>) suggestionRepository.findAll();
	}

	@Override
	public Suggestion createSuggestion(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	@Override
	public void deleteSuggestion(Suggestion suggestion) {
		suggestionRepository.delete(suggestion);
		
	}

}
