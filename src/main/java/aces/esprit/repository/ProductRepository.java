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


}
