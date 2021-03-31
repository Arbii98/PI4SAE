package aces.esprit.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.OneToMany;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("DeliveryMan")
public class DeliveryMan extends User implements Serializable  {
	
	
	@OneToMany(mappedBy="livreur")
	private List<Delivery> livraisons;
	
	@Enumerated(EnumType.STRING)
	private DeliveryManStatus etat;
	
	public DeliveryMan()
	{
		super();
	}
	
	
	public DeliveryMan(int id)
	{
		super(id);
	}
	
	
	
	

}
