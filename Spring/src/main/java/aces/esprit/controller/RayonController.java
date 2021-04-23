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

import aces.esprit.entity.Product;
import aces.esprit.entity.Rayon;
import aces.esprit.service.RayonService;

@RestController
public class RayonController {
	
	@Autowired
	RayonService rs;
	
	@PostMapping("/addRayon")
	@ResponseBody
	public Rayon addRayon(@RequestBody Rayon rayon) {
		return rs.addRayon(rayon);
	}
	
	
	@GetMapping("/getRayons")
	@ResponseBody
	public List<Rayon> getRayons() {
		return rs.getRayons();
	}
	
	
	@GetMapping("/getProducts/{idRayon}")
	@ResponseBody
	public List<Product> getProductsInRayon(@PathVariable("idRayon") int idRayon) {
		return rs.getProductsInRayon(idRayon);
	}
	
	
	@PutMapping("/addProductToRayon/{idRayon}/{idProduct}")
	@ResponseBody
	public void addProductToRayon(@PathVariable("idRayon") int idRayon,@PathVariable("idProduct") int idProduct) {
		rs.addProductToRayon(idProduct,idRayon);
	}
	
	
	@PutMapping("/removeProductFromRayon/{idRayon}/{idProduct}")
	@ResponseBody
	public void removeProductFromRayon(@PathVariable("idRayon") int idRayon,@PathVariable("idProduct") int idProduct) {
		rs.removeProductFromRayon(idProduct,idRayon);
	}
	
	
	@GetMapping("/findRayonByProduct/{idProduct}")
	@ResponseBody
	public Rayon findRayonByProduct(@PathVariable("idProduct") int idProduct) {
		return rs.findRayonByProduct(idProduct);
	}
	
	@DeleteMapping("/deleteRayon/{idRayon}")
	@ResponseBody
	public void deleteRayon(@PathVariable("idRayon") int idRayon)
	{
		rs.deleteRayon(idRayon);
	}
	
	
	
	
	

}
