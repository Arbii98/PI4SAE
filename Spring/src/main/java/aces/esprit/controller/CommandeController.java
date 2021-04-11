package aces.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Charge;

import aces.esprit.entity.Cart;
import aces.esprit.entity.Commande;
import aces.esprit.entity.StripeRequest;
import aces.esprit.service.*;

@RestController
public class CommandeController {
	@Autowired
	ICommandeService cs;
	
	
	@PostMapping("/addCommande/{idClient}")
	@ResponseBody
	public Commande addCommande(@RequestBody Commande commande,@PathVariable("idClient") int idClient) {
		return cs.addCommande(commande,idClient);
		
	}
	
	@PostMapping("/PayerCommande/{idCommande}")
	@ResponseBody
	public Charge payerCommande(@RequestBody StripeRequest stripe, @PathVariable("idCommande")int idCommande)
	{
		return cs.chargeNewCard(stripe,idCommande);
	}
	
	@PutMapping("/PayerCommandeManuellement/{idCommande}")
	@ResponseBody
	public void payerCommandeManuellement(@PathVariable("idCommande") int idCommande)
	{
		cs.marquerPaye(idCommande);
	}
}
