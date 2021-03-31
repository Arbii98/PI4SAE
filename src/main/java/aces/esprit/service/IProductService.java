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

import aces.esprit.entity.AgeRecommandationProduct;
import aces.esprit.entity.GenderRecommandation;
import aces.esprit.entity.Product;

public interface IProductService {
	
	void addProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(int id);
	void updateProduct(Product product, int idProd);
	void deleteProductById(int id);
	void deleteAllProducts();
	public void affectProductToCategory(int idProd, int idCat);
	public List<String> getAllProductsNamesByCategory(int idCat);

	int getNbrProducts();
	List<Product> getNewProducts();
	String readAndCheckImage(String path) throws FileNotFoundException, IOException, NotFoundException, ChecksumException, FormatException;
	
	void generateImageBarcode(int idProd, String image_name, String barcode);
	
	void generateImageBarcodee(String image_name, String barcode) throws Exception;
	
	byte[] genrateAndDownloadQRCode(String text, int width, int height, String filePath) throws Exception;
	byte[] getBarCodeImage(String text, int width, int height, String filePath) throws Exception;
	List<Product> RecommendedProduct(int id);

	
	List<String> search(String keyword);
	List<Product> searchDetails(String keyword);
	List<Product> recherche(String keyword);
	List<Product> getAllProductsByCategory(int idCat);
	List<Product> getAllProductsByNameCategory(String nomCat);
	List<Product> getAllProductsRecommandedForMen();
	List<Product> getAllProductsRecommandedForWemen();
	List<Product> getAllProductsRecommandedForChild();
	List<Product> getAllProductsRecommandedForJunior();
	List<Product> getAllProductsRecommandedForSenior();
	float StatisticsMan();
	float StatisticsWoman();
	float StatisticsChild();
	float StatisticsJunior();
	float StatisticsSenior();
	//void desaffecterProductFromCategory(int idProd, int idCat);
	
	Product addOneProduct(String name, String imgNameBarcode, String imgName, String barcode) throws Exception;
	
	Product ajouterProduit(String nameProd, String imageBarcode, String barcode, String imageProduct,
			String imageQRCodeName, String description, float price, AgeRecommandationProduct age,
			GenderRecommandation gender);
	List<Product> whishList(int idProd);
	List<Product> getTopProdsByPrice(int nbr);
	void desaffecterProductFromCategory(int idProd);
	void desaffecterProductFromAllCategory(int idCat);


	
}
