package aces.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class RatingPub implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int idRp;
	@Enumerated(EnumType.STRING)
	private ratPub rat;

	@ManyToOne
	@JsonIgnore
	private Publication pub;

	@ManyToOne
	@JsonIgnore
	private User user;

	public RatingPub(int idRp, ratPub rat, Publication pub, User user) {
		super();
		this.idRp = idRp;
		this.rat = rat;
		this.pub = pub;
		this.user = user;
	}

	public RatingPub() {
		super();
	}

	public int getIdRp() {
		return idRp;
	}

	public void setIdRp(int idRp) {
		this.idRp = idRp;
	}



	public ratPub getRat() {
		return rat;
	}

	public void setRat(ratPub rat) {
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

	public RatingPub(int idRp, ratPub rat) {
		super();
		this.idRp = idRp;
		this.rat = rat;
	}



}
