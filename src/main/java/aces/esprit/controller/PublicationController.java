package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Publication;
import aces.esprit.service.PublicationService;

@RestController
@RequestMapping("publication")
public class PublicationController {

	@Autowired
	PublicationService publicationService;
	
	@PostMapping("{idOwner}")
	public void addPublication(@RequestBody Publication publication,@PathVariable int idOwner) {
		publicationService.addPublication(publication,idOwner);
	}
	@PutMapping("{idPub}")
	public void updatePublication(@RequestBody Publication publication,@PathVariable int idPub) {
		publicationService.updatePublication(publication,idPub);
	}
	@DeleteMapping("{idPub}")
	public void deletePub(@PathVariable int idPub) {
		publicationService.deletePublication(idPub);
	}
	
	@GetMapping
	public List<Publication> getALL(){
		return publicationService.getallpub();
	}
	
	
}
