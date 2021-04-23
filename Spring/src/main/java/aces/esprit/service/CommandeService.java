package aces.esprit.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.Charge;

import aces.esprit.entity.Cart;
import aces.esprit.entity.Commande;
import aces.esprit.entity.User;
import aces.esprit.entity.OrderStatus;
import aces.esprit.entity.StripeRequest;
import aces.esprit.repository.CartRepository;
import aces.esprit.repository.CommandeRepository;
import aces.esprit.repository.UserRepository;

@Service
public class CommandeService implements ICommandeService{

	@Autowired
	CommandeRepository cr;

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	CartService cartService;
	
	@Override
	public Commande addCommande(Commande commande,int idClient) {
		 Date d = new Date();
		 commande.setDateOrder(d);
		 commande.setEtat(OrderStatus.NonPaye);
		 User client= (User)userRepository.findById(idClient).orElse(null);
		 List<Cart>PanierCourant = cartRepository.findByClientCourant(client);
		 float total=0;
		 
		 for(Cart c : PanierCourant)
		 {
			 total+=c.getTotal();
			 c.setCommande(commande);
		 }
		 commande.setTotal(total);
		 cr.save(commande);
		 int test;
		 for(Cart c : PanierCourant)
		 {
			 test=stockService.decrementStock(c.getProduit().getCurrentStock().getId(), c.getQuantite());
			 if(test!=1)
			 {
				 cartService.deleteCartsBecauseStock(c.getProduit());
			 }
			 cartRepository.save(c);
			 
		 }
		 
		 
		 
		 
		 
		
		return commande;
	}

	@Override
	public Charge chargeNewCard(StripeRequest stripe,int idCommande) {
		Commande commande = (Commande) cr.findById(idCommande).orElse(null);
		
		
		
		Stripe.apiKey="sk_test_20u9a1vhbiijZzdUsFvnxgaT00Xr4NPWPh";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(commande.getTotal() * 100));
        chargeParams.put("currency", stripe.getCurrency());
        chargeParams.put("source", stripe.getStripeToken());
        chargeParams.put("description",stripe.getDescription());
        try
        {
        	Charge charge = Charge.create(chargeParams);
        	commande.setEtat(OrderStatus.Paye);
        	cr.save(commande);
        	return charge;
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        return null;
	}

	@Override
	public void marquerPaye(int idCommande) {
		Commande commande = (Commande) cr.findById(idCommande).orElse(null);
    	cr.save(commande);

		
	}
	

}
