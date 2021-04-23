package aces.esprit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Archive implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idArchive;
	
	@Column(name = "name")
	private String nameArchive;
	
	@Column(name = "description")
	private String descriptionArchive;

	public int getIdArchive() {
		return idArchive;
	}

	public void setIdArchive(int idArchive) {
		this.idArchive = idArchive;
	}

	public String getNameArchive() {
		return nameArchive;
	}

	public void setNameArchive(String nameArchive) {
		this.nameArchive = nameArchive;
	}

	public String getDescriptionArchive() {
		return descriptionArchive;
	}

	public void setDescriptionArchive(String descriptionArchive) {
		this.descriptionArchive = descriptionArchive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
