package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("DeliveryMan")
public class DeliveryMan extends User implements Serializable  {
	
	
	@OneToMany(mappedBy="livreur")
	private List<Delivery> livraisons;
	
	

}
