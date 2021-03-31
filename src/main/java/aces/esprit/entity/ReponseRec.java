package aces.esprit.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
@Entity
public class ReponseRec implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Decision decision;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReponse;
	
	private String message;
	
	@OneToOne
	private Reclamation reclamation ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	public Date getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Reclamation getReclamation() {
		return reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}
	
	
	
	
	
	

}
