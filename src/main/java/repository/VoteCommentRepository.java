package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.CitizenDB;
import model.Comment;
import model.VoteComment;
import model.key.VoteCommentKey;

public interface VoteCommentRepository extends CrudRepository<VoteComment, Long>{

	List<VoteComment> findByComment(Comment comment);

	List<VoteComment> findByCitizenDB(CitizenDB citizen);

	VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);

}
