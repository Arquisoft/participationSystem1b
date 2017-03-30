package hello.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.model.CitizenDB;
import hello.model.Comment;
import hello.model.Suggestion;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

	List<Comment> fingBySuggestion(Suggestion suggestion);

	List<Comment> findByCitizenDB(CitizenDB citizenDB);

}
