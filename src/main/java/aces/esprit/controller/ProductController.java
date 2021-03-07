package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Product;
import aces.esprit.service.ICategoryService;
import aces.esprit.service.IProductService;

@RestController
public class ProductController {

	@Autowired
	IProductService iProductService;

	@Autowired
	ICategoryService iCategoryService;

	public ProductController() {
	}
	
	public ProductController(IProductService iProductService, ICategoryService iCategoryService) {
		this.iProductService = iProductService;
		this.iCategoryService = iCategoryService;
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/addProduct
	 */
	/*
	 * { "nameProduct": "iiiii", "descriptionProduct": "dddd", "priceProduct":
	 * 23, "dateCreationProduct": "2021-03-02", "imageFileNameProduct": "iiii",
	 * "videoFileNameProduct": "DD", "barcodeProduct": 5 }
	 */
	@PostMapping("/addProduct")
	@ResponseBody
	public Product addProduct(@RequestBody Product product) {
		iProductService.addProduct(product);
		return product;
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getProductById/1
	 */
	@GetMapping(value = "getProductById/{idProduct}")
	@ResponseBody
	public Product getProductById(@PathVariable("idProduct") int idP) {
		return iProductService.getProductById(idP);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getAllProducts
	 */
	@GetMapping(value = "/getAllProducts")
	@ResponseBody
	public List<Product> getAllProducts() {
		return iProductService.getAllProducts();
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getProductByName/aaa
	 */
	@GetMapping(value = "getProductByName/{nameProduct}")
	@ResponseBody
	public List<Product> getProductByName(@PathVariable("nameProduct") String nameP) {
		return iProductService.getProductByName(nameP);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/updateProduct/1
	 */
	@PutMapping("{idProd}")
	public void updateProduct(@RequestBody Product product, @PathVariable int idProd) {
		iProductService.updateProduct(product, idProd);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/deleteProductById/1
	 */
	@DeleteMapping("/deleteProductById/{idprod}")
	@ResponseBody
	public void deleteProductById(@PathVariable("idprod") int idProd) {
		iProductService.deleteProductById(idProd);

	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/deleteAllProducts
	 */
	@DeleteMapping("/deleteAllProducts")
	@ResponseBody
	public void deleteAllProducts() {
		iProductService.deleteAllProducts();

	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/affectProductToCategory/1/1
	 */
	@PutMapping(value = "/affectProductToCategory/{idProduct}/{idCategory}")
	public void affectProductToCategory(@PathVariable("idProduct") int idProd, @PathVariable("idCategory") int idCat) {
		iProductService.affectProductToCategory(idProd, idCat);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getAllProductsNamesByCategory/1
	 */
	@GetMapping(value = "getAllProductsNamesByCategory/{idCategory}")
	@ResponseBody
	public List<String> getAllProductsNamesByCategory(@PathVariable("idCategory") int idCategory) {
		return iProductService.getAllProductsNamesByCategory(idCategory);
	}

}
