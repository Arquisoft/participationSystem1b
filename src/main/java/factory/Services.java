package factory;


import services.CitizenDBService;
import services.CommentsService;
import services.SuggestionService;
import services.VoteCommentService;
import services.VoteSuggestionService;
import services.impl.CitizenDBServiceImpl;
import services.impl.CommentServiceImpl;
import services.impl.SuggestionServiceImpl;
import services.impl.VoteCommentServiceImpl;
import services.impl.VoteSuggestionServiceImpl;

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
