package model;

import org.junit.Test;

import model.key.VoteCommentKey;

/**
 * Clase de prueba para la realización
 * de las pruebas unitarias de la clase
 * VoteCommentKey
 *
 */
public class TestVoteCommentKey {

	
	private VoteCommentKey comment1 = new VoteCommentKey();
	private VoteCommentKey comment2 = new VoteCommentKey();
	
	public TestVoteCommentKey(){
		comment1.setCitizenDB((long)1);
		comment1.setComment((long)2);
		
		comment2.setCitizenDB((long)3);
		comment2.setComment((long)4);
	}
	
	
	@Test
	public void testEquals(){
		assert(!this.comment1.equals(comment2));
		
		VoteCommentKey copia1 = new VoteCommentKey();
		copia1.setCitizenDB(comment1.getCitizenDB());
		copia1.setComment(comment1.getComment());
		assert(this.comment1.equals(copia1));
		
		VoteCommentKey copia2 = new VoteCommentKey();
		copia2.setCitizenDB(comment2.getCitizenDB());
		copia2.setComment(comment2.getComment());
		assert(this.comment2.equals(copia2));
		
		assert(!this.comment1.equals(null));
		
		copia1.setCitizenDB(null);
		copia2.setComment(null);
		
		assert(!this.comment1.equals(copia1));
		assert(!this.comment2.equals(copia2));
	}
	
	
	@Test
	public void testHashCode(){
		
		VoteCommentKey copia1 = new VoteCommentKey();
		copia1.setCitizenDB(comment1.getCitizenDB());
		copia1.setComment(comment1.getComment());
		
		VoteCommentKey copia2 = new VoteCommentKey();
		copia2.setCitizenDB(comment2.getCitizenDB());
		copia2.setComment(comment2.getComment());
		
		assert(this.comment1.hashCode() == 994);
		assert(this.comment2.hashCode() == 1058);
		
		assert(this.comment1.hashCode() == copia1.hashCode());
		assert(this.comment2.hashCode() == copia2.hashCode());
	}
	
	
}
