package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;



@Entity
@Component
public class Delivery implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private DeliveryMan livreur;
	
	@OneToOne
	private Commande commande;
	
	
	private float cout;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus etat;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date DeliveryDate;
	
	
	public Delivery()
	{
		
	}
	
	public Delivery(int id)
	{
		this.id=id;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DeliveryMan getLivreur() {
		return livreur;
	}

	public void setLivreur(DeliveryMan livreur) {
		this.livreur = livreur;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public float getCout() {
		return cout;
	}

	public void setCout(float cout) {
		this.cout = cout;
	}

	public DeliveryStatus getEtat() {
		return etat;
	}

	public void setEtat(DeliveryStatus etat) {
		this.etat = etat;
	}

	public Date getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		DeliveryDate = deliveryDate;
	}
	
	

}
