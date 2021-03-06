package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
@JsonIgnoreProperties(value = { "produit" })
public class Stock  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private float quantite;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_produit")
	@JsonIgnore
	private Product produit;
	
	private float prixAchat;
	
	private float prixVente;
	
	private float quantiteVendu;
	
	@Temporal(TemporalType.DATE)
	private Date dateAchat;
	
	@Enumerated(EnumType.STRING)
	private StockStatus etat;
	

	public StockStatus getEtat() {
		return etat;
	}

	public void setEtat(StockStatus etat) {
		this.etat = etat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getQuantite() {
		return quantite;
	}

	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}

	public Product getProduit() {
		return produit;
	}

	public void setProduit(Product produit) {
		this.produit = produit;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	public float getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

	public float getQuantiteVendu() {
		return quantiteVendu;
	}

	public void setQuantiteVendu(float quantiteVendu) {
		this.quantiteVendu = quantiteVendu;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	
	
}
