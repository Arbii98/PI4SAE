package aces.esprit.controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.mail.iap.Response;

import aces.esprit.entity.AgeRecommandationProduct;
import aces.esprit.entity.GenderRecommandation;
import aces.esprit.entity.Product;
import aces.esprit.entity.Publication;
import aces.esprit.service.ICategoryService;
import aces.esprit.service.IProductService;
import aces.esprit.service.ProductService;
import aces.esprit.service.ZXingHelper;

@RestController
public class ProductController {

	@Autowired
	IProductService iProductService;

	@Autowired
	ICategoryService iCategoryService;
	
	private static String UPLOAD_DIR = "uploads";

	public ProductController() {
	}
	
	public ProductController(IProductService iProductService, ICategoryService iCategoryService) {
		this.iProductService = iProductService;
		this.iCategoryService = iCategoryService;
	}
	
	/****************************************Specific CRUD*********************************************/
	
	@PostMapping("/addProductWithoutGenerationgBarCode")
	@ResponseBody
	public Product a(@RequestParam("name") String name, 
			@RequestParam("image_barcode") MultipartFile file_image_barcode, 
			@RequestParam("image_product") MultipartFile file_image_product,
			HttpServletRequest request,
			HttpServletRequest request2,
			@RequestParam("description") String description,
			@RequestParam("price") float price,
			@RequestParam("recommanded_for_age") AgeRecommandationProduct age,
			@RequestParam("recommanded_for_gender") GenderRecommandation gender) throws Exception{
		
		String fileName_image_barcode = file_image_barcode.getOriginalFilename();
		String path_barcode = request.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName_image_barcode;
		saveFile(file_image_barcode.getInputStream(), path_barcode);
		
		String fileName_image_product = file_image_product.getOriginalFilename();
		String path_product = request2.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName_image_product;
		saveFile(file_image_product.getInputStream(), path_product);
		
		String barcode = iProductService.readAndCheckImage(path_barcode);
		//generate QRCode
		String imageQRCodeName = "QRCode"+name+".png";
		iProductService.genrateAndDownloadQRCode(name, 200, 200, "./src/main/webapp/QRCodeGenerator/"+imageQRCodeName);
		return iProductService.ajouterProduit(name, fileName_image_barcode, barcode, fileName_image_product, imageQRCodeName, description, price, age, gender);

	}
	
	@PostMapping("/addProductWithGenerationgBarCode")
	@ResponseBody
	public Product b(@RequestParam("name") String name, 
			@RequestParam("image_product") MultipartFile file_image_product,
			@RequestParam("barcode") String barcode,
			HttpServletRequest request,
			HttpServletRequest request2,
			@RequestParam("description") String description,
			@RequestParam("price") float price,
			@RequestParam("recommanded_for_age") AgeRecommandationProduct age,
			@RequestParam("recommanded_for_gender") GenderRecommandation gender) throws Exception{
		
		String fileName_image_product = file_image_product.getOriginalFilename();
		String path_product = request2.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName_image_product;
		saveFile(file_image_product.getInputStream(), path_product);
		//generate BarCode
		String imageBarcodeName = "BarCode"+barcode+".png";
		iProductService.getBarCodeImage(barcode, 200, 50, "./src/main/webapp/BarCodeGenerator/"+imageBarcodeName);
		//generate QRCode
		String imageQRCodeName = "QRCode"+name+".png";
		iProductService.genrateAndDownloadQRCode(name, 200, 200, "./src/main/webapp/QRCodeGenerator/"+imageQRCodeName);
		return iProductService.ajouterProduit(name, imageBarcodeName, barcode, fileName_image_product, imageQRCodeName, description, price, age, gender);

	}
	
	/**************************************** Normal CRUD *********************************************/
	
	@PostMapping("/addProduct")
	@ResponseBody
	public Product addProduct(@RequestBody Product product){	
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
	 * http://localhost:8081/SpringMVC/servlet/updateProduct/1
	 */
	@PutMapping(value = "updateProduct/{idProd}")
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
	
	
	/**************************************** QRCode *********************************************/

	
	//Generate QRCode
	@PostMapping("/genrateAndDownloadQRCode/{idProd}")
	@ResponseBody
	public void genrateAndDownloadQRCode(@PathVariable("idProd") String id, HttpServletResponse resp) throws Exception{
		
		resp.setContentType("image/png");
		
		OutputStream outputStream = resp.getOutputStream();
		System.out.println(outputStream);
		outputStream.write(iProductService.genrateAndDownloadQRCode(id, 200, 200, "./src/main/webapp/QRCodeGenerator/QRCode"+id+".png"));
		outputStream.flush();
		outputStream.close();
		
	}
	
	
	/**************************************** BarCode *********************************************/

	
	//Read BarCode
	@PostMapping(value = "/CheckBarCode")
	public String checkBarcode(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try{
			String fileName = file.getOriginalFilename();
			String path = request.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName;
			saveFile(file.getInputStream(), path);
			System.out.println(path);
			return iProductService.readAndCheckImage(path);
		}catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	private void saveFile(InputStream inputStream, String path){
		try{
			OutputStream outputStream = new FileOutputStream(new File(path));
			int read = 0;
			byte [] bytes = new byte[1024];
			while((read = inputStream.read(bytes)) != 1){
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
			outputStream.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Generate BarCode
	@PostMapping("/genrateAndDownloadBarCode/{idProd}")
	@ResponseBody
	public void genrateAndDownloadBarCode(@PathVariable("idProd") String id, HttpServletResponse resp) throws Exception{
		
		resp.setContentType("image/png");
		
		OutputStream outputStream = resp.getOutputStream();
		System.out.println(outputStream);
		outputStream.write(iProductService.getBarCodeImage(id, 200, 50, "./src/main/webapp/BarCodeGenerator/BarCode"+id+".png"));
		outputStream.flush();
		outputStream.close();		
	}
	
	
	/**************************************** Affect / Desaffect *********************************************/

	
	//metrics prod
	
	
	/*
	 * http://localhost:8081/SpringMVC/servlet/affectProductToCategory/1/1
	 */
	
	
	@PutMapping(value = "/affectProductToCategory/{idProduct}/{idCategory}")
	public void affectProductToCategory(@PathVariable("idProduct") int idProd, @PathVariable("idCategory") int idCat) {
		iProductService.affectProductToCategory(idProd, idCat);
	}
	
	
	/*@PutMapping(value = "/desaffecterProductFromCategory/{idProd}/{idCat}") 
	public void desaffecterProductFromCategory(@PathVariable("idProd")int idProd, @PathVariable("idCat")int idCat)
	{
		iProductService.desaffecterProductFromCategory(idProd, idCat);
	}*/
	

	@PutMapping(value = "/desaffecterProductFromCategory/{idCat}") 
	public void desaffecterProductFromCategory(@PathVariable("idCat")int idCat)
	{
		iProductService.desaffecterProductFromCategory(idCat);
	}
	
	@PutMapping(value = "/desaffecterProductFromAllCategory/{idCat}") 
	public void desaffecterProductFromAllCategory(@PathVariable("idCat")int idCat)
	{
		iProductService.desaffecterProductFromAllCategory(idCat);
	}
	
	
	
	
	
	
	
	/**************************************** Variety Products *********************************************/
	
	// Counting products
	@GetMapping(value = "/getNbrProds")
	@ResponseBody
	public int getNbProds() {
		return iProductService.getNbrProducts();
	}
	
	// Whishlist
	@GetMapping(value = "/ProductsByMarqueAndCategory/{idProd}")
	@ResponseBody
	public List<Product> test(@PathVariable("idProd") int idProd) {
		return iProductService.whishList(idProd);
	}
	
	//NEW Products
	@GetMapping(value = "/getNewProducts")
	@ResponseBody
	public List<Product> getNewProducts() {
		return iProductService.getNewProducts();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**************************************** Filters and Search *********************************************/

	// ok
	@GetMapping(value = "/getAllProductsNamesByCategory/{idCategory}")
	@ResponseBody
	public List<String> getAllProductsNamesByCategory(@PathVariable("idCategory") int idCategory) {
		return iProductService.getAllProductsNamesByCategory(idCategory);
	}

	// ok
	@GetMapping(value = "/getAllProductsByCategory/{idCategory}")
	@ResponseBody
	public List<Product> getAllProductsByCategory(@PathVariable("idCategory") int idCategory) {
		return iProductService.getAllProductsByCategory(idCategory);
	}

	// recherche par nom
	// ok
	@GetMapping(value = "/recherche/{prod}")
	@ResponseBody
	public List<Product> recherche(@PathVariable("prod") String keyword) {

		return iProductService.recherche(keyword);
	}
	
	@GetMapping(value = "/getTopProdsByPrice/{nbr}")
	@ResponseBody
	public List<Product> getTopProdsByPrice(@PathVariable("nbr") int nbr) {
		return iProductService.getTopProdsByPrice(nbr);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// New method of putting params and it works well
	// recherche detailed
	//http://localhost:8080/SpringMVC/servlet/search?term=chechia
	/*@GetMapping(value = "/search")
	@ResponseBody
	public String search(HttpServletRequest request) {
		
		String keyword = request.getParameter("term");
		Gson gson = new Gson();
		return gson.toJson(iProductService.search(keyword));
	}*/
	
	
	
	
	
	
	/**************************************** Recommandations *********************************************/

	// Front-office Principle Recommandation

	@GetMapping(value = "/recommendedProducts/{idUser}")
	@ResponseBody
	public List<Product> recommendedProducts(@PathVariable("idUser") int idUser) {
		System.out.println(" user id :" + idUser);
		return iProductService.RecommendedProduct(idUser);
	}

	// Back-office Recommandation

	@GetMapping(value = "/getAllProductsRecommandedForMen")
	@ResponseBody
	public List<Product> getAllProductsRecommandedForMen() {
		return iProductService.getAllProductsRecommandedForMen();
	}

	@GetMapping(value = "/getAllProductsRecommandedForWomen")
	@ResponseBody
	public List<Product> getAllProductsRecommandedForWomen() {
		return iProductService.getAllProductsRecommandedForWemen();
	}

	@GetMapping(value = "/getAllProductsRecommandedForChild")
	@ResponseBody
	public List<Product> getAllProductsRecommandedForChild() {
		return iProductService.getAllProductsRecommandedForChild();
	}

	@GetMapping(value = "/getAllProductsRecommandedForJunior")
	@ResponseBody
	public List<Product> getAllProductsRecommandedForJunior() {
		return iProductService.getAllProductsRecommandedForJunior();
	}

	@GetMapping(value = "/getAllProductsRecommandedForSenior")
	@ResponseBody
	public List<Product> getAllProductsRecommandedForSenior() {
		return iProductService.getAllProductsRecommandedForSenior();
	}
	

	
	/**************************************** Statistics *********************************************/

	
	// All Product statistics

	@GetMapping(value = "/manStatistics")
	@ResponseBody
	public float statisticsMan() {
		return iProductService.StatisticsMan();
	}

	@GetMapping(value = "/womanStatistics")
	@ResponseBody
	public float statisticsWoman() {
		return iProductService.StatisticsWoman();
	}

	@GetMapping(value = "/childStatistics")
	@ResponseBody
	public float statisticsChild() {
		return iProductService.StatisticsChild();
	}

	@GetMapping(value = "/juniortatistics")
	@ResponseBody
	public float statisticsJunior() {
		return iProductService.StatisticsJunior();
	}

	@GetMapping(value = "/seniorStatistics")
	@ResponseBody
	public float statisticsSenior() {
		return iProductService.StatisticsSenior();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//other generators
	
	
	
	@PostMapping("/generateBarcode/{idProduct}")
	@ResponseBody
	public void generateBarcode(@PathVariable("idProduct") int idProduct, @RequestParam("image_name") String image_name, @RequestParam("barcode") String barcode) {
		iProductService.generateImageBarcode(idProduct, image_name, barcode);
	}
	
	
	
	
	
	/////eeeeeeeeeee

	@PostMapping("/generateBarcodee")
	@ResponseBody
	public void generateBarcode(@RequestParam("image_name") String image_name, @RequestParam("barcode") String barcode, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		//outputStream.write(ProductService);
		iProductService.generateImageBarcodee(image_name, barcode);
	}

	
	
	
	
	
	
	
	
}
