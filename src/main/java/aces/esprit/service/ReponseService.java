package aces.esprit.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.ReponseRec;
import aces.esprit.repository.ReponseRepository;

@Service
public class ReponseService {
	
	@Autowired
	ReponseRepository rr;
	
	@Autowired
	ReclamationService rs;
	
	
	public ReponseRec addReponse(ReponseRec reponse)
	{
		Date today = new Date();
		reponse.setDateReponse(today);
		rr.save(reponse);
		
		rs.marquerTraitee(reponse.getReclamation().getId());
		
		return reponse;
	}
	

}
