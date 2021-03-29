package aces.esprit.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query("select p from Product p where p.nameProduct =: nameProd")
	//@Query("select p from Product p where p.nameProduct = ?1")

	List<Product> findByName(@Param("nameProd") String nameProd);
	
	@Query("select p from Product p where p.idProduct =:idprod")
	Product findByIdProduct(@Param("idprod") int idprod);
	
	@Query("select count(p.nameProduct) from Product p")
	int getNbProds();
	
	@Query(value="select * from product p where (p.gender = :gen "
			+ "and p.age = :ag) ",nativeQuery=true)
	List<Product> RecommendedProducts(@Param("gen") String gender,@Param("ag") String ss);



}
