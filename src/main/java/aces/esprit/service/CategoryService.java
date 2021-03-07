package aces.esprit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Category;
import aces.esprit.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public CategoryService() {
	}

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public void updateCategory(Category cat, int idCat) {
		if (categoryRepository.findById(idCat).orElse(null) != null)
			cat.setIdCategory(idCat);
		categoryRepository.save(cat);
	}

	@Override
	public void deleteCategoryById(int id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void deleteAllCategories() {
		categoryRepository.deleteAll();
	}

}
