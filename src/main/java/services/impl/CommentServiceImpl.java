package services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.CitizenDB;
import model.Comment;
import model.Suggestion;
import model.key.CommentKey;
import repository.CommentRepository;
import services.CommentsService;

public class CommentServiceImpl implements CommentsService{

	private CommentRepository commentRepository;

	@Autowired
	public void setCommentRepository(CommentRepository commentRepository){
		this.commentRepository = commentRepository;
	}

	@Override
	public List<Comment> findBySuggestion(Suggestion suggestion) {
		return commentRepository.fingBySuggestion(suggestion);
	}

	@Override
	public List<Comment> findByCitizenDB(CitizenDB citizenDB) {
		return commentRepository.findByCitizenDB(citizenDB);
	}

	@Override
	public List<Comment> findAll() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	@Override
	public void deleteComment(Comment comment) {
		 commentRepository.delete(comment);
	}

	@Override
	public Comment fingById(long id) {
		return commentRepository.findOne(id);
	}

}
