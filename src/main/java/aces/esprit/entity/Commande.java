package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;


@Entity
@Component
public class Commande implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;

	
	private float total;
	
	
	@Enumerated(EnumType.STRING)
	private OrderStatus etat;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOrder;
	
	
	@OneToMany(mappedBy="commande")
	private List<Cart> carts;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
	
	
}
