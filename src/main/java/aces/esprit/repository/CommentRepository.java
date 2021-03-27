package aces.esprit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import aces.esprit.entity.Comment;
import aces.esprit.entity.CommentPk;

public interface CommentRepository extends CrudRepository<Comment, CommentPk>{

	
	@Query("select c from Comment c where c.publication.idPub=:idPub order by c.commentPk.datecreation DESC ")
	List<Comment> findByPubOrderByDateCreation(@Param("idPub") int idPub);
	
	@Query(value = "SELECT count(*) FROM Comment c  where c.publication.idPub=:idPub")
	public Long countComment(@Param("idPub") int idPub);
}
