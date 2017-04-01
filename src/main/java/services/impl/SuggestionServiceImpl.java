package services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.CitizenDB;
import model.Suggestion;
import repository.SuggestionRepository;
import services.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService{

	private SuggestionRepository suggestionRepository;

	@Autowired
	public void setSuggestionRepository(SuggestionRepository suggestionRepository){
		this.suggestionRepository = suggestionRepository;
	}

	@Override
	public List<Suggestion> findByCitizenDB(CitizenDB citizenDB) {
		return suggestionRepository.findByCitizenDB(citizenDB);
	}

	@Override
	public Suggestion findByTitle(String title) {
		return suggestionRepository.findByTitle(title);
	}

	@Override
	public Suggestion findById(Long id){
		return suggestionRepository.findById(id);
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
