package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Reclamation implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@OneToOne
	private Delivery livraison;
	
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReclamation;
	
	@Enumerated(EnumType.STRING)
	private ReclamationStatus etatReclamation;
	
	@OneToOne(mappedBy="reclamation")
	private ReponseRec reponseReclamation;
	
	

}
