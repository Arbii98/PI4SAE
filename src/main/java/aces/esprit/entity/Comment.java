package aces.esprit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment implements Serializable {
	@EmbeddedId
	private CommentPk commentPk;
	
	private String description;
	
	
	
	public Comment() {
		super();
	}
	public Comment(CommentPk commentPk, String description) {
		super();
		this.commentPk = commentPk;
		this.description = description;
	}

	public CommentPk getCommentPk() {
		return commentPk;
	}
	public void setCommentPk(CommentPk commentPk) {
		this.commentPk = commentPk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "idPub", referencedColumnName = "idPub", insertable = false, updatable = false)
	@JsonIgnore
	private Publication publication;
	
	@OneToMany(mappedBy = "comment")
	//@JsonIgnore
	private List<RatingComment> ratingcomment;

}