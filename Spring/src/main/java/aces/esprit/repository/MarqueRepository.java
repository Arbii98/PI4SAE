package aces.esprit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Marque;

@Repository
public interface MarqueRepository extends CrudRepository<Marque, Integer>{

	
	@Query("select count(m.nameMarque) from Marque m")
	int getNbMarques();

}
