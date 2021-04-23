package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;


@Entity
@Component
public class Rayon implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	private String libelle;
	
	@OneToMany(mappedBy="rayon")
	private List<Product> produits;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Product> getProduits() {
		return produits;
	}

	public void setProduits(List<Product> produits) {
		this.produits = produits;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	

}
