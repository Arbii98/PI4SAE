package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCategory;
	
	@Column(name = "name")
	private String nameCategory;
	
	@Column(name = "description")
	private String descriptionCategory;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;

	public Category() {
		super();
	}
	
	public Category(int idCategory, String nameCategory, String descriptionCategory, List<Product> products) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.descriptionCategory = descriptionCategory;
		this.products = products;
	}

	public Category(int idCategory, String nameCategory, String descriptionCategory) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.descriptionCategory = descriptionCategory;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getDescriptionCategory() {
		return descriptionCategory;
	}

	public void setDescriptionCategory(String descriptionCategory) {
		this.descriptionCategory = descriptionCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
