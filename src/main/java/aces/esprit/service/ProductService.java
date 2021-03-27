package aces.esprit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Category;
import aces.esprit.entity.Product;
import aces.esprit.repository.CategoryRepository;
import aces.esprit.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	public ProductService() {
	}

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public void updateProduct(Product prod, int idProd) {
		if (productRepository.findById(idProd).orElse(null) != null)
			prod.setIdProduct(idProd);
		productRepository.save(prod);
	}

	@Override
	public void deleteProductById(int id) {
		productRepository.deleteById(id);
	}

	// didin't try it
	@Override
	public void deleteAllProducts() {
		productRepository.deleteAll();
	}

	public void affectProductToCategory(int idProd, int idCat) {
		Product productEntity = productRepository.findById(idProd).get();
		Category category = categoryRepository.findById(idCat).get();

		productEntity.setCategory(category);
		productRepository.save(productEntity);

	}

	public List<String> getAllProductsNamesByCategory(int idCat) {
		Category category = categoryRepository.findById(idCat).get();
		List<String> prodNames = new ArrayList<>();
		for (Product prod : category.getProducts()) {
			prodNames.add(prod.getNameProduct());
		}
		return prodNames;
	}

	

}
