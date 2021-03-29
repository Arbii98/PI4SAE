package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Userr implements Serializable{
	

	@Override
	public String toString() {
		return "User [id=" + id + ", messagesSent=" + messagesSent + ", messagesReceived=" + messagesReceived
				+ ", carts=" + carts + "]";
	}
	@Id
	private int id;
	
	private int age;
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private GenderUser gender;

	@Column(name = "interests")
	@Enumerated(EnumType.STRING)
	private InterestsRecommandation interests;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Userr(int id) {
		super();
		this.id = id;
	}

	public Userr() {
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
	
	
	
	@OneToMany(mappedBy="client")
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public GenderUser getGender() {
		return gender;
	}

	public void setGender(GenderUser gender) {
		this.gender = gender;
	}

	public InterestsRecommandation getInterests() {
		return interests;
	}

	public void setInterests(InterestsRecommandation interests) {
		this.interests = interests;
	}

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

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	

}
