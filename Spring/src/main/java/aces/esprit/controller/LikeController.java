package aces.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.service.ILikeSerive;
import aces.esprit.service.IProductService;

@RestController
public class LikeController {
	
	@Autowired
	IProductService iProductService;
	
	@Autowired
	ILikeSerive iLikeSerive;

	public LikeController() {
	}

	public LikeController(IProductService iProductService, ILikeSerive iLikeSerive) {
		this.iProductService = iProductService;
		this.iLikeSerive = iLikeSerive;
	}
	
	@PostMapping("/like/{idproduit}/{iduser}")
	@ResponseBody
	public void like(@PathVariable("idproduit") int idproduit,@PathVariable("iduser") int iduser) {
		iLikeSerive.like(idproduit,iduser);
		
	}
	

}
