package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.CitizenDB;
import model.Suggestion;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Long> {

	List<Suggestion> findByCitizenDB(CitizenDB citizenDB);
	List<Suggestion> findAll();
	
	Suggestion findByTitle(String title);

	Suggestion findById(Long id);	
}
