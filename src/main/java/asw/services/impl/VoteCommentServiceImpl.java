package asw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.model.CitizenDB;
import asw.model.Comment;
import asw.model.VoteComment;
import asw.model.key.VoteCommentKey;
import asw.repository.VoteCommentRepository;
import asw.services.VoteCommentService;

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
