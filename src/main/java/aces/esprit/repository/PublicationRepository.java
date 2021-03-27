package aces.esprit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import aces.esprit.entity.Publication;
import aces.esprit.entity.User;

public interface PublicationRepository extends CrudRepository<Publication, Integer>{


	@Query("select p from Publication p order by p.comments.size DESC")
	List<Publication> getAllPublish();
	

	@Query("select p from Publication p order by p.ratPub.size DESC")
	List<Publication> getAllPublishByTopRating();
	
	@Query("select  p from Publication p where userp.id =:id")
	List<Publication> getPubByUser(@Param("id") int idUser);
	
	@Query("select  p from Publication p where p.title =:title")
	Publication getPubByTitle(@Param("title") String title);
	
	@Query("select p from Publication p order by dateCreation desc")
	Page<Publication> getPub(Pageable pageable);
}