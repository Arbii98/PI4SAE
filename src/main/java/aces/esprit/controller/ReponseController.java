package aces.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	

}
