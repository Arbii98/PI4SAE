package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Entity
public class Cart implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	private User client;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	

	@OneToOne
	@JoinColumn(name="produit_id")
	private Product produit;
	
	
	private int quantite;
	
	private float total;
	
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@ManyToOne
	private Commande commande;
	
	public Cart()
	{
		super();
		Date date = new Date();
		this.dateCreation=date;
	}
	
	public Cart(int quantite,Product p,User c,Commande com)
	{
		this.quantite=quantite;
		this.produit=p;
		this.client=c;
		this.commande=com;
		Date date = new Date();
		this.dateCreation=date;
	}
	

	public long getId() {
		return id;
	}


	public void setId(int id) {
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

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
	
	

}
