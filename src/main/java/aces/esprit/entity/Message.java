package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int idMessage;

	@Column(columnDefinition = "MEDIUMTEXT")
	private String content;

	@ManyToOne
	@JoinColumn(name = "id_sender")
	// @JsonManagedReference
	private User sender;

	@ManyToOne
	@JoinColumn(name = "id_receiver")
	private User receiver;

	@Temporal(TemporalType.DATE)
	private Date date;

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Message(int idMessage, String content, User sender, User receiver, Date date) {
		super();
		this.idMessage = idMessage;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
	}

	public Message() {
		super();
	}

}
