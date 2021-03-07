package aces.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Cart implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne
	private User client;
	
	
	@OneToOne
	@JoinColumn(name="produit_id")
	private Product produit;
	
	
	private int quantite;
	
	
	@ManyToOne
	private Commande commande;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public User getClient() {
		return client;
	}


	public void setClient(User client) {
		this.client = client;
	}


	public Product getProduit() {
		return produit;
	}


	public void setProduit(Product produit) {
		this.produit = produit;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	/*public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}*/
	
	
	

}
