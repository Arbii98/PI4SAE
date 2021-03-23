package aces.esprit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Cart;
import aces.esprit.entity.Product;
import aces.esprit.entity.User;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>{

	
	
	@Query("select c from Cart c where c.client =:client and c.produit=:produit and c.commande = null")
	List<Cart> findByClientAndProduit( @Param("client") User client, @Param("produit")Product produit);
	
	
	@Query("select c from Cart c where c.client =:client and c.commande = null")
	List<Cart> findByClientCourant( @Param("client") User client);
	
	
	List<Cart> findByClient(User client);

	@Query("select c from Cart c where c.client =:client and c.commande = null")
	List<Cart> findByClientWithoutCommande(@Param("client") User client);
	
	
	@Transactional
	@Modifying
	@Query(value="delete from cart where commande_id is NULL and date_creation < CURRENT_DATE - 2",nativeQuery = true)
	void DeleteOldUnusedCarts();
	
	/*
	@Query("delete from Cart c where c.id=:idCart")
	void deleteById(@Param("idCart")int id);*/
	
	@Transactional
	@Modifying
	@Query("delete from Cart c where c=:cart")
	void delete(@Param("cart") Cart cart);
	
	
	@Transactional
	@Modifying
	@Query("delete from Cart c where c.id=:id")
	void deleteById(@Param("id") int id);



}
