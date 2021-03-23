package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@DiscriminatorValue("Admin")
public class Admin extends User implements Serializable  {
	
	public Admin()
	{
		super();
	}
	
	public Admin(int id)
	{
		super(id);
	}
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE)
	List<Publication> publications;

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}
	

}
