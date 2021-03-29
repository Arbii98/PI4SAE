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

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.mail.iap.Response;

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
	public Product addProduct(@RequestBody Product product){
		
		/*resp2.setContentType("image/png");

		
		OutputStream outputStream2 = resp.getOutputStream();
		outputStream2.write(iProductService.getBarCodeImage(String.valueOf(product.getIdProduct()), 200, 50, "./src/main/webapp/BarCodeGenerator/BarCode"+String.valueOf(product.getIdProduct())+".png"));
		outputStream2.flush();
		outputStream2.close();*/
		
		iProductService.addProduct(product);
		
		return product;
	}
	
	@PostMapping("/addA/{idProd}")
	@ResponseBody
    public Product saveProduct(
    		@PathVariable("idProd") int idProd,
    		@RequestParam("name") String name, 
    		HttpServletRequest request,
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("barcode") String barcode,
    		@RequestParam("generate") boolean generateBarcode,
    		HttpServletResponse resp) throws Exception{
   
			/*String fileName2 = "";
			fileName2 = file2.getOriginalFilename();
			String path2 = request.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName2;
			saveFile(file2.getInputStream(), path2);
			String a = iProductService.readAndCheckImage(path2);*/
		
		//String fileName = null;
		String fileName = file.getOriginalFilename();
		String path = request.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName;
		saveFile(file.getInputStream(), path);
		if ( generateBarcode == false ){
			
		
			barcode = iProductService.readAndCheckImage(path);
	    	return iProductService.addNewProduct(idProd, name, fileName, barcode, generateBarcode);

		}
		else {
				file = null;
				iProductService.generateImageBarcodee(name, barcode);
		    	return iProductService.addNewProduct(idProd, name, name, barcode, generateBarcode);

		}
		
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
	@GetMapping(value = "/getAllProductsNamesByCategory/{idCategory}")
	@ResponseBody
	public List<String> getAllProductsNamesByCategory(@PathVariable("idCategory") int idCategory) {
		return iProductService.getAllProductsNamesByCategory(idCategory);
	}
	
	@GetMapping(value = "/getNbrProds")
	@ResponseBody
	public int getNbProds() {
		return iProductService.getNbrProducts();
	}
	
	/*
	 * http://localhost:8081/SpringMVC/servlet/getNewProducts
	 */
	@GetMapping(value = "/getNewProducts")
	@ResponseBody
	public List<Product> getNewProducts() {
		return iProductService.getNewProducts();
	}

	private static String UPLOAD_DIR = "uploads";
	
	@PostMapping(value = "/up")
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
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
	
	@PostMapping("/generateBarcode/{idProduct}")
	@ResponseBody
	public void generateBarcode(@PathVariable("idProduct") int idProduct, @RequestParam("image_name") String image_name, @RequestParam("barcode") String barcode) {
		iProductService.generateImageBarcode(idProduct, image_name, barcode);
	}

	@PostMapping("/generateBarcodee")
	@ResponseBody
	public void generateBarcode(@RequestParam("image_name") String image_name, @RequestParam("barcode") String barcode, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		//outputStream.write(ProductService);
		iProductService.generateImageBarcodee(image_name, barcode);
	}

	@PostMapping("/genrateAndDownloadQRCode/{id}")
	@ResponseBody
	public void genrateAndDownloadQRCode(@PathVariable("id") String id, HttpServletResponse resp) throws Exception{
		
		resp.setContentType("image/png");
		
		OutputStream outputStream = resp.getOutputStream();
		System.out.println(outputStream);
		outputStream.write(iProductService.genrateAndDownloadQRCode(id, 200, 200, "./src/main/webapp/QRCodeGenerator/QRCode"+id+".png"));
		outputStream.flush();
		outputStream.close();
		
	}
	
	@PostMapping("/genrateAndDownloadBarCode/{id}")
	@ResponseBody
	public void genrateAndDownloadBarCode(@PathVariable("id") String id, HttpServletResponse resp) throws Exception{
		
		resp.setContentType("image/png");
		
		OutputStream outputStream = resp.getOutputStream();
		System.out.println(outputStream);
		outputStream.write(iProductService.getBarCodeImage(id, 200, 50, "./src/main/webapp/BarCodeGenerator/BarCode"+id+".png"));
		outputStream.flush();
		outputStream.close();
		//iProductService.getBarCodeImage(id, 200, 50, "./src/main/webapp/BarCodeGenerator/BarCode"+id+".png");
		
	}
	
	@GetMapping(value = "/recommendedProducts/{id}")
	@ResponseBody
	public List<Product> recommendedProducts(@PathVariable("id") int id) {
		System.out.println(" user id :"+id);
		return iProductService.RecommendedProduct(id);
	}
	
}
