package aces.esprit.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Cart;
import aces.esprit.entity.Commande;
import aces.esprit.entity.User;
import aces.esprit.entity.OrderStatus;
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
	

}
