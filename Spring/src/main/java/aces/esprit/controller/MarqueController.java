package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Marque;
import aces.esprit.service.IMarqueService;

@RestController
public class MarqueController {
	
	@Autowired
	IMarqueService iMarqueService;
	
	//@Autowired
	//ArchiveService archiveService;

	public MarqueController() {
	}

	public MarqueController(IMarqueService iMarqueService) {
		this.iMarqueService = iMarqueService;
	}


	@PostMapping("/addMarque")
	@ResponseBody
	public Marque addCategory(@RequestBody Marque marque) {
		iMarqueService.addMarque(marque);
		return marque;
	}

	@GetMapping(value = "getMarqueById/{idMarque}")
	@ResponseBody
	public Marque getMarqueById(@PathVariable("idMarque") int idMar) {
		return iMarqueService.getMarqueById(idMar);
	}


	@GetMapping(value = "/getAllMarques")
	@ResponseBody
	public List<Marque> getAllMarques() {
		return iMarqueService.getAllMarques();
	}


	@PutMapping(value = "updateMarque/{idMar}")
	public void updateMarque(@RequestBody Marque marque, @PathVariable int idMar) {
		iMarqueService.updateMarque(marque, idMar);
	}

	@DeleteMapping("/deleteMarqueById/{idmar}")
	@ResponseBody
	public void deleteMarqueById(@PathVariable("idmar") int idMar) {
		
		iMarqueService.deleteMarqueById(idMar);

	}

	@DeleteMapping("/deleteAllMarques")
	@ResponseBody
	public void deleteAllMarques() {
		iMarqueService.deleteAllMarques();

	}
	
	@GetMapping(value = "/getNbrMarques")
	@ResponseBody
	public int getNbMarques() {
		return iMarqueService.getNbrMarques();
	}

}
