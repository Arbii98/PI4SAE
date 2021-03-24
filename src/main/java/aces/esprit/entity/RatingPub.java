package aces.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RatingPub implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRp;
	@DecimalMax("5.00")
	private float rat;

	@ManyToOne
	@JsonIgnore
	private Publication pub;

	@ManyToOne
	@JsonIgnore
	private User user;

	public RatingPub() {
		super();
	}

	public int getIdRp() {
		return idRp;
	}

	public void setIdRp(int idRp) {
		this.idRp = idRp;
	}

	public float getRat() {
		return rat;
	}

	public void setRat(float rat) {
		this.rat = rat;
	}

	public Publication getPub() {
		return pub;
	}

	public void setPub(Publication pub) {
		this.pub = pub;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RatingPub(int idRp, float rat) {
		super();
		this.idRp = idRp;
		this.rat = rat;
	}

}
