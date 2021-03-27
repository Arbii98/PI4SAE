package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

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
	private int id;

	private int banned;
	@Enumerated(EnumType.STRING)
	private GenderUser gender; 
	
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBanned() {
		return banned;
	}

	public GenderUser getGender() {
		return gender;
	}

	public void setGender(GenderUser gender) {
		this.gender = gender;
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

}
