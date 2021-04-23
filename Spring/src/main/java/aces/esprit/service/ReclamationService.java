package aces.esprit.service;


import java.util.*;
import aces.esprit.entity.User;
import aces.esprit.entity.Commande;
import aces.esprit.entity.Reclamation;
import aces.esprit.entity.ReclamationLivreurStat;
import aces.esprit.entity.ReclamationStatus;
import aces.esprit.repository.CommandeRepository;
import aces.esprit.repository.ReclamationRepository;
import aces.esprit.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamationService {
	
	
	@Autowired
	ReclamationRepository rr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	CommandeRepository cr;
	
	public Reclamation addReclamation(Reclamation reclamation)
	{
		Date d =new Date();
		reclamation.setDateReclamation(d);
		reclamation.setEtatReclamation(ReclamationStatus.NONTRAITEE);
		
		return rr.save(reclamation);
		
	}
	
	
	public List<Reclamation> getAllReclamations()
	{
		List<Reclamation> myList = (List<Reclamation>) rr.findAll();
		for(Reclamation rec : myList)
		{
			rec.getLivraison().setCommande(null);
		}
		return myList;
	}
	
	
	public List<Reclamation> getReclamationsTraitees()
	{
		List<Reclamation> myList = rr.findAllTraitees();
		for(Reclamation rec : myList)
		{
			rec.getLivraison().setCommande(null);
		}
		return myList;
		
	}
	
	public List<Reclamation> getReclamationsNonTraitees()
	{
		List<Reclamation> myList = rr.findAllNonTraitees();
		for(Reclamation rec : myList)
		{
			rec.getLivraison().setCommande(null);
		}
		return myList;
	}
	
	public List<Reclamation> getReclamationsEnCours()
	{
		List<Reclamation> myList = rr.findAllEnCours();
		for(Reclamation rec : myList)
		{
			rec.getLivraison().setCommande(null);
		}
		return myList;
	}

	public List<Reclamation> getReclamationsByClient(int idClient)
	{
		List<Reclamation> first = (List<Reclamation>) rr.findAll();
		List<Reclamation> second = new ArrayList<Reclamation>();
		for(Reclamation rec : first)
		{
			if(rec.getLivraison().getCommande().getCarts().get(0).getClient().getId()==idClient)
			{
				rec.getLivraison().setCommande(null);
				second.add(rec);
				
			}
		}
		
		return second;
	}
	
	
	public List<ReclamationLivreurStat> getReclamationsByLivreur()
	{
		return rr.getReclamationsByLivreur();
	}
	
	
	public void marquerEnCours(int idReclamation)
	{
		Reclamation rec = (Reclamation) rr.findById(idReclamation).orElse(null);
		rec.setEtatReclamation(ReclamationStatus.ENCOURS);
		rr.save(rec);
	}
	
	public void marquerTraitee(int idReclamation)
	{
		Reclamation rec = (Reclamation) rr.findById(idReclamation).orElse(null);
		rec.setEtatReclamation(ReclamationStatus.TRAITEE);
		rr.save(rec);
	}
	
	public void marquerNonTraitee(int idReclamation)
	{
		Reclamation rec = (Reclamation) rr.findById(idReclamation).orElse(null);
		rec.setEtatReclamation(ReclamationStatus.NONTRAITEE);
		rr.save(rec);
	}
	
}
