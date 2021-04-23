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

	@Query(value="SELECT sum(c.total) as somme,pr.name FROM cart c join product pr on c.produit_id=pr.id_product where  c.commande_id is not null group by pr.id_product ",nativeQuery = true)
	List<Object[]> GetRepartitionRevenus();
	
	
	@Query(value="SELECT sum(c.total) as somme,u.id FROM cart c join user u on c.client_id=u.id where  c.commande_id is not null group by u.id ",nativeQuery = true)
	List<Object[]> GetClientsFideles();
	
	@Query("select c from Cart c where c.client =:client and c.produit=:produit and c.commande = null")
	List<Cart> findByClientAndProduit( @Param("client") User client, @Param("produit")Product produit);
	
	
	@Query("select c from Cart c where c.client =:client and c.commande = null")
	List<Cart> findByClientCourant( @Param("client") User client);
	
	
	List<Cart> findByClient(User client);

	@Query("select c from Cart c where c.client =:client and c.commande = null")
	List<Cart> findByClientWithoutCommande(@Param("client") User client);
	
	@Transactional
	@Modifying
	@Query("delete Cart c where c.produit =:produit and c.commande = null")
	void deleteByProduitWithoutCommande(@Param("produit") Product produit);
	
	@Transactional
	@Modifying
	@Query("delete Cart c where c.quantite>:qte and c.produit=:produit and c.commande is null")
	void deleteBiggerThanStock(@Param("qte") int quantite, @Param("produit") Product produit);
	
	
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
