package aces.esprit.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Publication;
import aces.esprit.service.EmailService;
import aces.esprit.service.PublicationService;

@RestController
@RequestMapping("publication")
public class PublicationController {

	@Autowired
	PublicationService publicationService;
	@Autowired
    private EmailService emailService;

	@PostMapping("{userp}")
	public ResponseEntity<Void> addPublication(@Valid @RequestBody Publication publication, @PathVariable int userp) {
		if(publicationService.addPublication(publication, userp) != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping("{idPub}")
	public ResponseEntity<Void> updatePublication(@RequestBody Publication publication, @PathVariable int idPub) {
		if(publicationService.updatePublication(publication, idPub) != null)
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("{idPub}")
	public void deletePub(@PathVariable int idPub) {
		publicationService.deletePublication(idPub);
	}

	@GetMapping(value = "/getAllPublishByTopComment")
	public List<Publication> getallpubByTopComment() {
		return publicationService.getallpubByTopComment();
	}
	
	@GetMapping(value = "/getAllPublishByTopRating")
	public List<Publication> getAllPublishByTopRating() {
		return publicationService.getAllPublishByTopRating();
	}
	@GetMapping(value = "/getscann/{idPub}")
	public Map<String, Float> IAScanner(@PathVariable int idPub){
		return publicationService.IAScanner(idPub);
		
	}
	@GetMapping(value = "/getallbubbyuser/{idUser}")
	public List<Publication> getAllPubByUser(@PathVariable int idUser){
		return publicationService.getAllPubByUser(idUser);
		
	}
	@GetMapping(value = "/getpubTitle/{title}")
	public ResponseEntity<Void> getByTitle(@PathVariable String title){
		if( publicationService.getByTitle(title)!= null) 
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	
	}
	@GetMapping("/pub")
	public ResponseEntity<Map<String, Object>> getPub(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return new ResponseEntity<>(publicationService.getPub(page, size), HttpStatus.OK);
		
			
	}
	@GetMapping("/nblikepub/{idPub}")
	public Map<String, Integer> nbrLike(@PathVariable int idPub){
		return publicationService.nbrLike(idPub);
		
	}

	
	
			
 
	

	
}
