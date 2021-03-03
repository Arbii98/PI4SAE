package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Publication implements Serializable {
	@Id
	private int idPub;
	private String title;
	private String description;
	private Date dateCreation;
	
	@ManyToOne
	private Admin owner;
	public Admin getOwner() {
		return owner;
	}
	public void setOwner(Admin owner) {
		this.owner = owner;
	}
	public int getIdPub() {
		return idPub;
	}
	public void setIdPub(int idPub) {
		this.idPub = idPub;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Publication( String title, String description, Date dateCreation) {
		super();
		this.title = title;
		this.description = description;
		this.dateCreation = dateCreation;
	}
	public Publication() {
		super();
	}
	
	
	
	
	
}
