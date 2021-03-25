package aces.esprit.service;


import java.util.*;
import aces.esprit.entity.Reclamation;
import aces.esprit.entity.ReclamationStatus;

import aces.esprit.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamationService {
	
	
	@Autowired
	ReclamationRepository rr;
	
	public Reclamation addReclamation(Reclamation reclamation)
	{
		Date d =new Date();
		reclamation.setDateReclamation(d);
		reclamation.setEtatReclamation(ReclamationStatus.NONTRAITEE);
		
		return rr.save(reclamation);
		
	}
	

}
