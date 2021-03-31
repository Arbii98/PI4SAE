package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Marque implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMarque;
	
	@Column(name = "name")
	private String nameMarque;
	
	@Column(name = "description")
	private String descriptionMarque;
	
	@JsonIgnore
	@OneToMany(mappedBy = "marque", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private List<Product> products;

	public Marque() {
	}

	public int getIdMarque() {
		return idMarque;
	}

	public void setIdMarque(int idMarque) {
		this.idMarque = idMarque;
	}

	public String getNameMarque() {
		return nameMarque;
	}

	public void setNameMarque(String nameMarque) {
		this.nameMarque = nameMarque;
	}

	public String getDescriptionMarque() {
		return descriptionMarque;
	}

	public void setDescriptionMarque(String descriptionMarque) {
		this.descriptionMarque = descriptionMarque;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
