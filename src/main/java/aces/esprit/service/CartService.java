package aces.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Admin;
import aces.esprit.entity.Cart;
import aces.esprit.entity.Product;
import aces.esprit.entity.User;
import aces.esprit.repository.CartRepository;
import aces.esprit.repository.ProductRepository;
import aces.esprit.repository.StockRepository;
import aces.esprit.repository.UserRepository;

import java.util.Date;
import java.util.List;


@Service
public class CartService implements ICartService{

	@Autowired
	CartRepository cr;
	@Autowired
	ProductRepository pr;
	@Autowired
	UserRepository userRepository;
	@Autowired
	StockService ss;
	
	



	@Override
	public Cart addCart(Cart cart) {
		Date date = new Date();
		List<Cart>PanierCourant = cr.findByClientCourant(cart.getClient());
		for(Cart c : PanierCourant)
		{
			c.setDateCreation(date);
			cr.save(c);
		}
		
		List<Cart> liste =cr.findByClientAndProduit(cart.getClient(), cart.getProduit());
		if(liste.size()>0)
		{
			liste.get(0).setQuantite(liste.get(0).getQuantite()+cart.getQuantite());
			if(liste.get(0).getQuantite()>ss.getQuantiteRestante(liste.get(0).getProduit().getCurrentStock().getId()))
			{
				liste.get(0).setQuantite(ss.getQuantiteRestante(liste.get(0).getProduit().getCurrentStock().getId()));
			}
			
			liste.get(0).setTotal(liste.get(0).getQuantite()*liste.get(0).getProduit().getPriceProduct());
			liste.get(0).setDateCreation(date);
			cr.save(liste.get(0));
			return liste.get(0);
		}
		else
		{
 			Product prod = (Product) pr.findById(cart.getProduit().getIdProduct()).orElse(null);
			cart.setProduit(prod);
			if(cart.getQuantite()>ss.getQuantiteRestante(prod.getCurrentStock().getId()))
			{
				cart.setQuantite(ss.getQuantiteRestante(prod.getCurrentStock().getId()));
				
			}
			cart.setTotal(cart.getProduit().getPriceProduct()*cart.getQuantite());
			
			cr.save(cart);
			return cart;
		}
		
	}
	


	@Override
	public List<Cart> getCarts() {
		return (List<Cart>) cr.findAll();
	}


	@Override
	public List<Cart> GetCurrentCartsForClient(int idclient) {
		User client = (User) userRepository.findById(idclient).orElse(null);
		return cr.findByClientWithoutCommande(client);
	}



	@Override
	public Cart decrementCart(int idCart, int quantite) {
		
		
		
		
		Cart cart = (Cart) cr.findById(idCart).orElse(null);
		cart.setQuantite(cart.getQuantite()-quantite);
		cart.setTotal(cart.getProduit().getPriceProduct()*cart.getQuantite());
		
		if(cart.getQuantite()<=0)
		{
			cr.delete(cart);
			return null;

		}
		else
		{
			Date date = new Date();
			cart.setDateCreation(date);
			cr.save(cart);
			
			
			List<Cart>PanierCourant = cr.findByClientCourant(cart.getClient());
			for(Cart c : PanierCourant)
			{
				c.setDateCreation(date);
				cr.save(c);
			}
			
			
			return cart;
			
		}
	}



	@Override
	public void deleteCart(int idCart) {
		cr.deleteById(idCart);
	}



	@Override
	public void deleteUnusedCarts() {
		cr.DeleteOldUnusedCarts();
		
	}



	@Override
	public void deleteCartsBecauseStock(Product produit) {
		cr.deleteByProduitWithoutCommande(produit);
	}



	@Override
	public void deleteBiggerThanStock(int qte,Product produit) {
		cr.deleteBiggerThanStock(qte,produit);
		
	}
	
	
	


	

}
