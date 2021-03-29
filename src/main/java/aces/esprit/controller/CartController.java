package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Cart;
import aces.esprit.entity.Product;
import aces.esprit.service.CartService;
import aces.esprit.service.ICartService;

@RestController
public class CartController {
	
	@Autowired
	ICartService cs;
	
	/*
	 * http://localhost:8081/SpringMVC/servlet/addCart
	 */
	@PostMapping("/addCart")
	@ResponseBody
	public Cart addCart(@RequestBody Cart cart) {
		return cs.addCart(cart);
		
	}
	
	
	@GetMapping(value = "/getCarts")
	@ResponseBody
	public List<Cart> getCarts() {
		return cs.getCarts();
	}
	
	@GetMapping(value = "/GetClientsFideles")
	@ResponseBody
	public List<Object[]> GetClientsFideles() {
		return cs.GetClientsFideles();
	}
	
	@GetMapping(value = "/GetRepartitionRevenus")
	@ResponseBody
	public List<Object[]> GetRepartitionRevenus() {
		return cs.GetRepartitionRevenus();
	}
	
	
	@GetMapping(value = "/GetCurrentCartsForClient/{idclient}")
	@ResponseBody
	public List<Cart> GetCurrentCartsForClient(@PathVariable("idclient") int idclient) {
		return cs.GetCurrentCartsForClient(idclient);
	}
	
	
	/*
	 * http://localhost:8081/SpringMVC/servlet/decrementCart/
	 */
	@PutMapping("/decrementCart/{idCart}/{quantite}")
	public Cart decrementCart(@PathVariable("idCart") int idCart, @PathVariable("quantite") int quantite) {
		return cs.decrementCart(idCart,quantite);
	}
	
	
	@DeleteMapping("/deleteCart/{idCart}")
	@ResponseBody
	public void deleteCart(@PathVariable("idCart") int idCart) {
		int id = (int) idCart;
		cs.deleteCart(id);
	}
	
	
	
	
	
	
	@Scheduled(fixedRate=60000)
	@DeleteMapping("/deleteCarts")
	@ResponseBody
	public void deleteAllOldCarts() {
		cs.deleteUnusedCarts();
	}
	
	
	
	
	

	
	
	

}
