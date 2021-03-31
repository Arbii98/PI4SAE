package aces.esprit.service;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Reclamation;
import aces.esprit.entity.ReponseRec;
import aces.esprit.repository.ReclamationRepository;
import aces.esprit.repository.ReponseRepository;
import java.util.*;

@Service
public class ReponseService {
	
	@Autowired
	ReponseRepository rr;
	
	@Autowired
	ReclamationRepository reclamationRepository;
	
	@Autowired
	ReclamationService rs;
	
	
	public ReponseRec addReponse(ReponseRec reponse)
	{
		Date today = new Date();
		reponse.setDateReponse(today);
		rr.save(reponse);
		
		rs.marquerTraitee(reponse.getReclamation().getId());
		
		Reclamation rec = (Reclamation) reclamationRepository.findById(reponse.getReclamation().getId()).orElse(null);
		rec.setReponseReclamation(reponse);
		
		reclamationRepository.save(rec);
		
		
		return reponse;
	}
	
	
	public long getTempsAttenteReclamation(int idReclamation)
	{
		Reclamation rec = (Reclamation) reclamationRepository.findById(idReclamation).orElse(null);
		
		long duree = Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
		return duree;
		
		
		
	}
	
	public long getTempsAttenteMoyenReclamation() {
		
		long total=0;
		List<Reclamation> myList = reclamationRepository.findAllTraitees();
		for(Reclamation rec : myList)
		{
			total+= Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
		}
		return total/myList.size();
	}

}
