package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
