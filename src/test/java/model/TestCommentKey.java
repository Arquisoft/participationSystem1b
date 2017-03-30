package model;

import org.junit.Test;

import hello.model.key.CommentKey;

/**
 * Clase para realizar las pruebas
 * unitarias de la clase CommentKey
 *
 */
public class TestCommentKey {

	private CommentKey comment1 = new CommentKey();
	private CommentKey comment2 = new CommentKey();
	
	public TestCommentKey(){
		comment1.setCitizenDB((long)1);
		comment1.setSuggestion((long)2);
		
		comment2.setCitizenDB((long)3);
		comment2.setSuggestion((long)4);
	}
	
	
	@Test
	public void testEquals(){
		assert(!this.comment1.equals(comment2));
		
		CommentKey copia1 = new CommentKey();
		copia1.setCitizenDB(comment1.getCitizenDB());
		copia1.setSuggestion(comment1.getSuggestion());
		assert(this.comment1.equals(copia1));
		
		CommentKey copia2 = new CommentKey();
		copia2.setCitizenDB(comment2.getCitizenDB());
		copia2.setSuggestion(comment2.getSuggestion());
		assert(this.comment2.equals(copia2));
		
		assert(!this.comment1.equals(null));
		
		copia1.setCitizenDB(null);
		copia2.setSuggestion(null);
		
		assert(!this.comment1.equals(copia1));
		assert(!this.comment2.equals(copia2));
	}
	
	@Test
	public void testHashCode(){
		CommentKey copia1 = new CommentKey();
		copia1.setCitizenDB(comment1.getCitizenDB());
		copia1.setSuggestion(comment1.getSuggestion());
		
		CommentKey copia2 = new CommentKey();
		copia2.setCitizenDB(comment2.getCitizenDB());
		copia2.setSuggestion(comment2.getSuggestion());
		
		assert(this.comment1.hashCode() == 994);
		assert(this.comment2.hashCode() == 1058);
		
		assert(this.comment1.hashCode() == copia1.hashCode());
		assert(this.comment2.hashCode() == copia2.hashCode());
	}
	
}
