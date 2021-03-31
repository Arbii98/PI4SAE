package aces.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import aces.esprit.entity.ReponseRec;
import aces.esprit.service.ReponseService;

@RestController
public class ReponseController {
	
	@Autowired
	ReponseService rs;
	
	
	@PostMapping("/addReponse")
	@ResponseBody
	public ReponseRec addReponse(@RequestBody ReponseRec reponse)
	{
		return rs.addReponse(reponse);
	}

	
	@GetMapping("/getTempsAttenteReclamation/{idReclamation}")
	@ResponseBody
	public long getTempsAttenteReclamation(@PathVariable("idReclamation") int idRec)
	{
		return rs.getTempsAttenteReclamation(idRec);
	}
	
	@GetMapping("/getTempsAttenteMoyenReclamation")
	@ResponseBody
	public long getTempsAttenteMoyenReclamation()
	{
		return rs.getTempsAttenteMoyenReclamation();
	}
	

}
