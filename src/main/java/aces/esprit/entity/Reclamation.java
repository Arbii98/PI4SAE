package aces.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Reclamation implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@OneToOne
	private Delivery livraison;

}
