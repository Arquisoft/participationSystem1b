package services;

import java.util.List;

import model.CitizenDB;
import model.Comment;
import model.Suggestion;

public interface CommentsService {
	
	List<Comment> findBySuggestion(Suggestion suggestion);
	List<Comment> findByCitizenDB(CitizenDB citizenDB);
	List<Comment> findAll();
	Comment createComment(Comment comment);
	void deleteComment(Comment comment);
}
