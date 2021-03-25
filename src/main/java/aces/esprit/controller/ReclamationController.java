package aces.esprit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import aces.esprit.entity.Reclamation;
import aces.esprit.service.ReclamationService;
import aces.esprit.service.EmailService;




@RestController
public class ReclamationController {
	
	@Autowired
	ReclamationService rs;
	
	@Autowired
	EmailService emailService;
	
	
	@PostMapping("/addReclamation")
	@ResponseBody
	public Reclamation addReclamation(@RequestBody Reclamation reclamation ) {
		emailService.sendMailReclamation("fairouz.benjeddou@gmail.com", "Reclamation");
		return rs.addReclamation(reclamation);
		
	}

}
