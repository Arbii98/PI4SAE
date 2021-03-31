package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publication implements Serializable {
	@Override
	public String toString() {
		return "Publication [idPub=" + idPub + ", title=" + title + ", description=" + description + ", dateCreation="
				+ dateCreation + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int idPub;
	
	@NotEmpty(message = "Please provide a Title")
	private String title;
	
	@NotEmpty(message = "Please provide a description for your Publication")
	private String description;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateCreation;

	@OneToMany(mappedBy = "publication")
	private List<Comment> comments;

	@OneToMany(mappedBy = "pub", fetch = FetchType.EAGER)
	private List<RatingPub> ratPub;

	public List<RatingPub> getRatPub() {
		return ratPub;
	}

	public void setRatPub(List<RatingPub> ratPub) {
		this.ratPub = ratPub;
	}

	@ManyToOne
	private User userp;

	public User getUserp() {
		return userp;
	}

	public void setUserp(User userp) {
		this.userp = userp;
	}

	public int getIdPub() {
		return idPub;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setIdPub(int idPub) {
		this.idPub = idPub;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Publication(String title, String description, Date dateCreation) {
		super();
		this.title = title;
		this.description = description;
		this.dateCreation = dateCreation;
	}

	public Publication(int idPub, String title, String description, Date dateCreation) {
		super();
		this.idPub = idPub;
		this.title = title;
		this.description = description;
		this.dateCreation = dateCreation;

	}

	public Publication() {
		super();
	}

}
