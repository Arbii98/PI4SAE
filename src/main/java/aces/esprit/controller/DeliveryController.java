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
import aces.esprit.entity.Delivery;
import aces.esprit.service.DeliveryService;


@RestController
public class DeliveryController {
	
	@Autowired
	DeliveryService ds;
	
	@PostMapping("/addDelivery")
	@ResponseBody
	public Delivery addDelivery(@RequestBody Delivery d ) {
		return ds.addDelivery(d);
	}
	
	
	
	@DeleteMapping("/deleteDelivery/{id}")
	@ResponseBody
	public void deleteDelivery(@PathVariable("id") int idDelivery) {
		ds.deleteDelivery(idDelivery);
	}
	
	
	@PutMapping("/enAttenteDelivery/{id}")
	@ResponseBody
	public void enAttenteDelivery(@PathVariable("id") int idDelivery) {
		ds.marquerEnAttente(idDelivery);
	}
	
	
	@PutMapping("/enCoursDelivery/{id}")
	@ResponseBody
	public void enCoursDelivery(@PathVariable("id") int idDelivery) {
		ds.marquerEnCours(idDelivery);
	}
	
	
	@PutMapping("/doneDelivery/{id}")
	@ResponseBody
	public void doneDelivery(@PathVariable("id") int idDelivery) {
		ds.marquerDone(idDelivery);
	}
	
	@GetMapping("/getCurrentDeliveriesForDeliveryMan/{idDeliveryMan}")
	@ResponseBody
	public List<Delivery> getCurrentDeliveriesForDeliveryMan(@PathVariable("idDeliveryMan") int id){
		return ds.getCurrentDeliveriesForDeliveryMan(id);
	}
	
	
	@GetMapping("/getCurrentDeliveries")
	@ResponseBody
	public List<Delivery> getCurrentDeliveries(){
		return ds.getCurrentDeliveries();
	}
	
	
	@GetMapping("/getHistoryDeliveries")
	@ResponseBody
	public List<Delivery> getHistoryDeliveries(){
		return ds.getHistoryDeliveries();
	}
	
	
	@GetMapping("/getTempsAttenteDelivery/{idDelivery}")
	@ResponseBody
	public long getTempsAttenteDelivery(@PathVariable("idDelivery") int idDelivery){
		return ds.getTempsAttenteDelivery(idDelivery);
	}
	
	@GetMapping("/getTempsAttenteMoyen")
	@ResponseBody
	public long getTempsAttenteMoyen(){
		return ds.getTempsAttenteMoyen();
	}
	
	
	
	
	

}
