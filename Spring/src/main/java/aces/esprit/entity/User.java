package aces.esprit.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@DiscriminatorValue("User")
public class User implements Serializable {

	
	@Override
	public String toString() {
		return "User [id=" + id + ", messagesSent=" + messagesSent + ", messagesReceived=" + messagesReceived
				+ ", carts=" + carts + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonIgnore
	private int id;
	private String name;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String image ;
	
	public String getImage() {
		return image;
	}





	public void setImage(String image) {
		this.image = image;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	private int banned;




	public User(int id, int banned, boolean block, List<Message> messagesSent, List<Message> messagesReceived,
			List<RatingComment> rating, List<Publication> publications, List<Cart> carts,
			List<LikeProduct> likeProducts) {
		super();
		this.id = id;
		this.banned = banned;
	
		this.messagesSent = messagesSent;
		this.messagesReceived = messagesReceived;
		this.rating = rating;
		this.publications = publications;
		this.carts = carts;
		this.likeProducts = likeProducts;
	}















	public int getBanned() {
		return banned;
	}



	public void setBanned(int banned) {
		this.banned = banned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User(int id) {
		this.id = id;
	}

	public User() {
		super();
	}

	@OneToMany(mappedBy = "sender")
	@JsonIgnore
	private List<Message> messagesSent;

	@OneToMany(mappedBy = "receiver")
	@JsonIgnore
	private List<Message> messagesReceived;

	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<RatingComment> rating;

	@JsonIgnore
	@OneToMany(mappedBy = "userp", cascade = CascadeType.REMOVE)
	List<Publication> publications;

	public List<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public List<RatingComment> getRating() {
		return rating;
	}

	public void setRating(List<RatingComment> rating) {
		this.rating = rating;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	@OneToMany(mappedBy = "client")
	private List<Cart> carts;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<LikeProduct> likeProducts;



	public List<LikeProduct> getLikeProducts() {
		return likeProducts;
	}

	public void setLikeProducts(List<LikeProduct> likeProducts) {
		this.likeProducts = likeProducts;
	}



}
