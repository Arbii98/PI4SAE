package aces.esprit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.Publication;
import aces.esprit.entity.RatingPub;
import aces.esprit.entity.User;

public interface RatingPubRepository extends CrudRepository<RatingPub, Integer>{

	
	
	List<RatingPub> getByPubAndUser(Publication pub,User user  );
	
}
