package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.model.Suggestion;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

}
