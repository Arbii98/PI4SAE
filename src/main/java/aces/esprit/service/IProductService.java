package aces.esprit.service;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

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
<<<<<<< Updated upstream
	
=======
	int getNbrProducts();
	List<Product> getNewProducts();
	String readAndCheckImage(String path) throws FileNotFoundException, IOException, NotFoundException, ChecksumException, FormatException;
	
	void generateImageBarcode(int idProd, String image_name, String barcode);
	
	void generateImageBarcodee(String image_name, String barcode) throws Exception;
	
	byte[] genrateAndDownloadQRCode(String text, int width, int height, String filePath) throws Exception;
	byte[] getBarCodeImage(String text, int width, int height, String filePath) throws Exception;
	List<Product> RecommendedProduct(int id);
>>>>>>> Stashed changes

	Product addNewProduct(int idProd, String name, String imgNameBarcode, String barcode, boolean generateBarcode)
			throws Exception;
	
	


	
}
