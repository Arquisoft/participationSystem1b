package hello.factory;


import hello.services.CitizenDBService;
import hello.services.CommentsService;
import hello.services.SuggestionService;
import hello.services.VoteCommentService;
import hello.services.VoteSuggestionService;
import hello.services.impl.CitizenDBServiceImpl;
import hello.services.impl.CommentServiceImpl;
import hello.services.impl.SuggestionServiceImpl;
import hello.services.impl.VoteCommentServiceImpl;
import hello.services.impl.VoteSuggestionServiceImpl;

public class Services {
	
	public static CitizenDBService getCitizenDBService(){
		return new CitizenDBServiceImpl();
	}
	
	public static SuggestionService getSuggestionService(){
		return new SuggestionServiceImpl();
	}
	
	public static CommentsService getCommentsService(){
		return new CommentServiceImpl();
	}
	
	public static VoteCommentService getVoteCommentService(){
		return new VoteCommentServiceImpl();
	}
	
	public static VoteSuggestionService getVoteSuggestionService(){
		return new VoteSuggestionServiceImpl();
	}

}
