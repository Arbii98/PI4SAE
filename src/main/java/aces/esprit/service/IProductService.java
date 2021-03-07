package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Product;

public interface IProductService {
	
	void addProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(int id);
	List<Product> getProductByName(String name);
	void updateProduct(Product product, int idProd);
	void deleteProductById(int id);
	void deleteAllProducts();
	public void affectProductToCategory(int idProd, int idCat);
	public List<String> getAllProductsNamesByCategory(int idCat);

}
