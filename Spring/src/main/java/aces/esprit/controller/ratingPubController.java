package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.RatingPub;
import aces.esprit.service.PublicationService;

@RestController
@RequestMapping("ratingpub")
public class ratingPubController {
	@Autowired
	PublicationService publicationService;

	@GetMapping()
	public List<RatingPub> getratingPub() {
		return publicationService.getRatingPub();
	}

	@PostMapping("{idUser}/{idPub}")
	public ResponseEntity<Void> affectRatForPub(@RequestBody RatingPub ratp, @PathVariable int idUser,
			@PathVariable int idPub) {
		if (publicationService.affectRatForPub(ratp, idUser, idPub) != null)

			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}
