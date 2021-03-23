package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Cart;

public interface ICartService {

	Cart addCart(Cart cart);
	List<Cart> getCarts();
	List<Cart> GetCurrentCartsForClient(int idclient);
	Cart decrementCart(int idCart, int quantite);
	void deleteCart(int idCart);
	void deleteUnusedCarts();
}
