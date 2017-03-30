package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.CitizenDB;
import model.Comment;
import model.Suggestion;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

	List<Comment> fingBySuggestion(Suggestion suggestion);

	List<Comment> findByCitizenDB(CitizenDB citizenDB);

}
