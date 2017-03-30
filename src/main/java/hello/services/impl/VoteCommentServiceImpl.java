package hello.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hello.model.CitizenDB;
import hello.model.Comment;
import hello.model.VoteComment;
import hello.model.key.VoteCommentKey;
import hello.repository.VoteCommentRepository;
import hello.services.VoteCommentService;

public class VoteCommentServiceImpl implements VoteCommentService{

	@Autowired
	private VoteCommentRepository voteCommentRepository;

	@Override
	public List<VoteComment> findByComment(Comment comment) {
		return voteCommentRepository.findByComment(comment);
	}

	@Override
	public List<VoteComment> findByCitizenDB(CitizenDB citizen) {
		return voteCommentRepository.findByCitizenDB(citizen);
	}

	@Override
	public VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey) {
		return voteCommentRepository.findByVoteCommentKey(voteCommentKey);
	}

	@Override
	public VoteComment createVoteComment(VoteComment voteComment) {
		return voteCommentRepository.save(voteComment);
	}

	@Override
	public void deleteVoteComment(VoteComment voteComment) {
		voteCommentRepository.delete(voteComment);
		
	}

}
