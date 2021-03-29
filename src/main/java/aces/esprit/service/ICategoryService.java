package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Category;

public interface ICategoryService {
	
	void addCategory(Category category);
	List<Category> getAllCategories();
	Category getCategoryById(int id);
	void updateCategory(Category category, int idCat);
	void deleteCategoryById(int id);
	void deleteAllCategories();
	int getNbrCategories();

}
