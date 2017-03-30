package hello.services;

import java.util.List;

import hello.model.CitizenDB;
import hello.model.Comment;
import hello.model.VoteComment;
import hello.model.key.VoteCommentKey;


public interface VoteCommentService {

	List<VoteComment> findByComment (Comment comment);
	List<VoteComment> findByCitizenDB(CitizenDB citizen);
	VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);
	
	VoteComment createVoteComment (VoteComment voteComment);
	void deleteVoteComment (VoteComment voteComment);
}
