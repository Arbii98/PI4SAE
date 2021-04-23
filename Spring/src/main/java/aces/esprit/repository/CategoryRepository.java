package aces.esprit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	@Query("select count(c.nameCategory) from Category c")
	int getNbCategories();

}
