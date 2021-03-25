package aces.esprit.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import aces.esprit.entity.Reclamation;



@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Integer> {
	
	

}
