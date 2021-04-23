package aces.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.LikeProduct;
import aces.esprit.entity.Product;

@Repository
public interface LikeRepository extends CrudRepository<LikeProduct, Integer>{
	
	/*@Query("select l.product "
			+ "from LikedProduct l "
			+ "where l.user.id = :idUser")
	public List<Product> getAllProductsByUser(@Param("idUser") int id);*/

}
