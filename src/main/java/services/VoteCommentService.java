package services;

import java.util.List;

import model.CitizenDB;
import model.Comment;
import model.VoteComment;
import model.key.VoteCommentKey;


public interface VoteCommentService {

	List<VoteComment> findByComment (Comment comment);
	List<VoteComment> findByCitizenDB(CitizenDB citizen);
	VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);
	
	VoteComment createVoteComment (VoteComment voteComment);
	void deleteVoteComment (VoteComment voteComment);
}
