package aces.esprit.service;

import com.stripe.model.Charge;

import aces.esprit.entity.Commande;
import aces.esprit.entity.StripeRequest;

public interface ICommandeService {
	
	Commande addCommande(Commande commande,int idClient);
	Charge chargeNewCard(StripeRequest stripe,int idCommande);
	void marquerPaye(int idCommande);

}
