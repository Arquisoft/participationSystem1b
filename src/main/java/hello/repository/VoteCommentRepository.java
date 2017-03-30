package hello.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hello.model.CitizenDB;
import hello.model.Comment;
import hello.model.VoteComment;
import hello.model.key.VoteCommentKey;

public interface VoteCommentRepository extends CrudRepository<VoteComment, Long>{

	List<VoteComment> findByComment(Comment comment);

	List<VoteComment> findByCitizenDB(CitizenDB citizen);

	VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);

}
