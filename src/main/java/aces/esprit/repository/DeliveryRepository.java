package aces.esprit.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Delivery;
import java.util.List;
import aces.esprit.entity.DeliveryStatus;
import org.springframework.data.repository.query.Param;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Integer>{
	
	
	@Query("select d from Delivery d where d.etat != :done and d.livreur.id=:idLivreur ")
	List<Delivery> getCurrentDeliveriesForDeliveryMan(@Param("done") DeliveryStatus etat, @Param("idLivreur") int id);
	 
	
	@Query("select d from Delivery d where d.etat != :done")
	List<Delivery> getCurrentDeliveries(@Param("done") DeliveryStatus etat);
	
	
	@Query("select d from Delivery d where d.etat = :done")
	List<Delivery> getHistoryDeliveries(@Param("done") DeliveryStatus etat);
	

}
