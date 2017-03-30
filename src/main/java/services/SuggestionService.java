package services;

import java.util.List;

import model.CitizenDB;
import model.Suggestion;

public interface SuggestionService {

	List<Suggestion> findByCitizenDB(CitizenDB citizenDB);
	Suggestion findByTitle(String title);
	List<Suggestion> findAll();
	
    Suggestion createSuggestion(Suggestion suggestion);
    void deleteSuggestion(Suggestion suggestion);
}
