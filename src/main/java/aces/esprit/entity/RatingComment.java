package aces.esprit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RatingComment implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idR;
	@Enumerated(EnumType.STRING)
	private TypeRating typerating;
	
	@ManyToOne
	private Comment comment;
	@ManyToOne
	private User users;

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public RatingComment() {
		super();
	}

	public RatingComment(int idR, TypeRating typerating) {
		super();
		this.idR = idR;
		this.typerating = typerating;
	}

	public int getIdR() {
		return idR;
	}

	public void setIdR(int idR) {
		this.idR = idR;
	}

	public TypeRating getTyperating() {
		return typerating;
	}

	public void setTyperating(TypeRating typerating) {
		this.typerating = typerating;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	

}
