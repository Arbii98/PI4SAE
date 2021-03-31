package aces.esprit.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import aces.esprit.entity.Commande;
import aces.esprit.entity.Reclamation;
import aces.esprit.entity.ReclamationLivreurStat;
import aces.esprit.entity.User;



@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Integer> {
	
	
	@Query("select r from Reclamation r where r.etatReclamation='TRAITEE'")
	List<Reclamation>findAllTraitees();
	
	
	@Query("select r from Reclamation r where r.etatReclamation='NONTRAITEE'")
	List<Reclamation>findAllNonTraitees();
	
	@Query("select r from Reclamation r where r.etatReclamation='ENCOURS'")
	List<Reclamation>findAllEnCours();

	
	
	@Query("select count(r) as count,r.livraison.livreur as livreur from Reclamation r group by r.livraison.livreur")
	List<ReclamationLivreurStat> getReclamationsByLivreur();
	
	
	
	
	
	

}
