package aces.esprit.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	

	@Query("select p from Product p where p.idProduct =:idprod")
	Product findByIdProduct(@Param("idprod") int idprod);
	
	@Query("select count(p.nameProduct) from Product p")
	int getNbProds();
	
	@Query(value="select * from product p where (p.gender = :gen "
			+ "and p.age = :ag) ",nativeQuery=true)
	List<Product> RecommendedProducts(@Param("gen") String gender,@Param("ag") String ss);


	@Query("select nameProduct from Product where nameProduct like :keyword")
	List<String> search(@Param("keyword") String keyword);
	
	@Query("from Product where nameProduct like :keyword")
	List<Product> searchDetails(@Param("keyword") String keyword);
	
	@Query(value="select * from product p where p.name = :keyword",nativeQuery=true)
	List<Product> rechercheProd(@Param("keyword") String keyword);

	
	
	@Query("from Product p "
			+ "where p.gender = aces.esprit.entity.GenderRecommandation.MAN")
	public List<Product> getAllProductsRecommandedForMen();
	
	@Query("from Product p "
			+ "where p.gender = aces.esprit.entity.GenderRecommandation.WOMAN")
	public List<Product> getAllProductsRecommandedForWomen();
	
	@Query("from Product p "
			+ "where p.age = aces.esprit.entity.AgeRecommandationProduct.CHILD")
	public List<Product> getAllProductsRecommandedForChild();
	
	@Query("from Product p "
			+ "where p.age = aces.esprit.entity.AgeRecommandationProduct.JUNIOR")
	public List<Product> getAllProductsRecommandedForJunior();
	
	@Query("from Product p "
			+ "where p.age = aces.esprit.entity.AgeRecommandationProduct.SENIOR")
	public List<Product> getAllProductsRecommandedForSenior();
	
	
	
	
}
