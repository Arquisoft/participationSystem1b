package hello.services;

import java.util.List;

import hello.model.CitizenDB;
import hello.model.Suggestion;

public interface SuggestionService {

	List<Suggestion> findByCitizenDB(CitizenDB citizenDB);
	Suggestion findByTitle(String title);
	List<Suggestion> findAll();
	
    Suggestion createSuggestion(Suggestion suggestion);
    void deleteSuggestion(Suggestion suggestion);
}
