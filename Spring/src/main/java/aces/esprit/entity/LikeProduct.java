package aces.esprit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LikeProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLike;
	
	@Column(name = "name")
	@Enumerated(EnumType.STRING)
	private LikedProduct nameLike;
	
	private int nbrview;
	
	@ManyToOne
	@JoinColumn(name = "fkIdProduct")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "fkIdUser")
	private User user;

	public LikeProduct() {
	}



	public LikeProduct(int idLike, LikedProduct nameLike, int nbrLikes, Product product, User user) {
		super();
		this.idLike = idLike;
		this.nameLike = nameLike;
		this.product = product;
		this.user = user;
	}



	public int getIdLike() {
		return idLike;
	}

	public void setIdLike(int idLike) {
		this.idLike = idLike;
	}



	public LikedProduct getNameLike() {
		return nameLike;
	}



	public void setNameLike(LikedProduct nameLike) {
		this.nameLike = nameLike;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int getNbrview() {
		return nbrview;
	}



	public void setNbrview(int nbrview) {
		this.nbrview = nbrview;
	}
	
	
	
	
}
