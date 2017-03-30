package hello.services;

import java.util.List;

import hello.model.CitizenDB;
import hello.model.Comment;
import hello.model.Suggestion;

public interface CommentsService {
	
	List<Comment> findBySuggestion(Suggestion suggestion);
	List<Comment> findByCitizenDB(CitizenDB citizenDB);
	List<Comment> findAll();
	Comment createComment(Comment comment);
	void deleteComment(Comment comment);
	
	

}
