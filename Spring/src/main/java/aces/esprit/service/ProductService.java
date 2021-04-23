package aces.esprit.service;

import java.io.FileInputStream;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.util.StringUtils;
import java.util.Base64;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import aces.esprit.entity.Advertising;
import aces.esprit.entity.AgeRecommandationProduct;
import aces.esprit.entity.Category;
import aces.esprit.entity.GenderRecommandation;
import aces.esprit.entity.LikeProduct;
import aces.esprit.entity.LikedProduct;
import aces.esprit.entity.Marque;
import aces.esprit.entity.Product;
import aces.esprit.entity.User;
import aces.esprit.entity.Userr;
import aces.esprit.repository.CategoryRepository;
import aces.esprit.repository.LikeRepository;
import aces.esprit.repository.ProductRepository;
import aces.esprit.repository.UserrRepository;
import javassist.bytecode.stackmap.BasicBlock.Catch;

@Service
public class ProductService implements IProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserrRepository userrRepository;
	
	@Autowired
	LikeRepository likeRepository;
	
	public ProductService() {
	}

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	//Adding product with or without generationg BarCode
	@Override
	public Product ajouterProduit(String nameProd, 
			String imageBarcode, 
			String barcode, 
			String imageProduct, 
			String imageQRCodeName,
			String description,
			float price,
			AgeRecommandationProduct age,
			GenderRecommandation gender){
		
		Product p = new Product();
		p.setNameProduct(nameProd);
		p.setImageBarcodeFileNameProduct(imageBarcode);
		p.setBarcodeProduct(barcode);
		p.setImageFileNameProduct(imageProduct);
		p.setQrCodeImageProduct(imageQRCodeName);
		p.setDescriptionProduct(description);
		p.setPriceProduct(price);
		p.setGender(gender);
		p.setAge(age);
		
		//affectation des deux cates
		Date today = new Date();
	    Calendar c1 = Calendar.getInstance(); 
	    c1.setTime(today); 
	    today = c1.getTime();
	    p.setDateCreationProduct(today);
	    	    
	    Date remainingDate = new Date();
	    Calendar c = Calendar.getInstance(); 
	    c.setTime(remainingDate); 
	    c.add(Calendar.DATE, 7);
	    remainingDate = c.getTime();
	    p.setDateEndNewProduct(remainingDate);

		return productRepository.save(p);
	}

	//Normal add without any image
	@Override
	public void addProduct(Product product) {
		
		Date today = new Date();
	    Calendar c1 = Calendar.getInstance(); 
	    c1.setTime(today); 
	    today = c1.getTime();
	    product.setDateCreationProduct(today);
	    	    
	    Date remainingDate = new Date();
	    Calendar c = Calendar.getInstance(); 
	    c.setTime(remainingDate); 
	    c.add(Calendar.DATE, 7);
	    remainingDate = c.getTime();
	    product.setDateEndNewProduct(remainingDate);
	    
		productRepository.save(product);
	}
	
	@Override
	public Product addOneProduct(String name,String imgNameBarcode, String imgName, String barcode) throws Exception {
		
		//Product p = productRepository.findById(idProd).get();

		Product p = new Product();
		
		p.setImageBarcodeFileNameProduct(imgNameBarcode);
		p.setImageFileNameProduct(imgName);

		
		Date today = new Date();
	    Calendar c1 = Calendar.getInstance(); 
	    c1.setTime(today); 
	    today = c1.getTime();
	    p.setDateCreationProduct(today);
	    
	    	    
	    Date remainingDate = new Date();
	    Calendar c = Calendar.getInstance(); 
	    c.setTime(remainingDate); 
	    c.add(Calendar.DATE, 7);
	    remainingDate = c.getTime();
	    p.setDateEndNewProduct(remainingDate);
	  
	    p.setNameProduct(name);
	    p.setBarcodeProduct(barcode);

	    int idProd = p.getIdProduct();
	    
	    String imageName = "QRCode"+String.valueOf(idProd)+".png";
	    System.out.println(imageName);
	    
	    genrateAndDownloadQRCode(String.valueOf(idProd), 200, 200, "./src/main/webapp/QRCodeGenerator/"+imageName);
	    
	    p.setQrCodeImageProduct(imageName);
	    	    
        return productRepository.save(p);
	}
	
	public void genrateeQRCode(String text, int width, int height, String filePath) throws Exception{

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
		
		Path path = FileSystems.getDefault().getPath(filePath);
		
		FileOutputStream out = new FileOutputStream(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
				
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
	public void updateProduct(Product prod, int idProd) {
		if (productRepository.findById(idProd).orElse(null) != null)
			prod.setIdProduct(idProd);
		productRepository.save(prod);
	}

	@Override
	public void deleteProductById(int id) {
		productRepository.deleteById(id);
	}
	
	@Override
	public int getNbrProducts() {
		return productRepository.getNbProds();
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
	
	@Override
	@Transactional
	public void desaffecterProductFromCategory(int idProd)
	{
		Product productEntity = productRepository.findById(idProd).get();
		//Category category = categoryRepository.findById(idCat).get();

		productEntity.setCategory(null);
		productRepository.save(productEntity);
		/*
		Category cat = categoryRepository.findById(idCat).get();
		
		int prodNb = cat.getProducts().size();
		for(int index = 0; index < prodNb; index++){
			if(cat.getProducts().get(index).getIdProduct() == idProd){
				cat.getProducts().remove(index);
				break;
			}
		}*/
	}
	
	@Override
	@Transactional
	public void desaffecterProductFromAllCategory(int idCat)
	{
		//Product productEntity = productRepository.findById(idProd).get();
		//Category category = categoryRepository.findById(idCat).get();

		//productEntity.setCategory(category);
		//productRepository.save(productEntity);
		
		Category cat = categoryRepository.findById(idCat).get();
		List<Product> listP = (List<Product>) productRepository.findAll();
		
		for (Product p:listP){
			if (p.getCategory() == cat){
				productRepository.delete(p);
			}
		}
		
		/*int prodNb = cat.getProducts().size();
		for(int index = 0; index < prodNb; index++){
			if(cat.getProducts().get(index).getIdProduct() == idProd){
				cat.getProducts().remove(index);
				break;
			}
		}*/
	}

	//check ken tekhdem wale
	public List<String> getAllProductsNamesByCategory(int idCat) {
		Category category = categoryRepository.findById(idCat).get();
		List<String> prodNames = new ArrayList<>();
		for (Product prod : category.getProducts()) {
			prodNames.add(prod.getNameProduct());
		}
		return prodNames;
	}

	public List<Product> getNewProducts(){
				
		Date dt1 = new Date();
	    Calendar c1 = Calendar.getInstance(); 
	    c1.setTime(dt1); 
	    dt1 = c1.getTime();
	    System.out.println(dt1);
	    
	    Date dt = new Date();
	    Calendar c = Calendar.getInstance(); 
	    c.setTime(dt); 
	    c.add(Calendar.DATE, 7);
	    dt = c.getTime();
		
	    System.out.println(dt);
	    	    
		List<Product> newProds = new ArrayList<>();
	    
		List<Product> prods = (List<Product>) productRepository.findAll();

	    for (Product pp: prods) {
	    	if((pp.getDateCreationProduct().after(dt1) || pp.getDateCreationProduct().equals(dt1)) 
		    		&& (pp.getDateCreationProduct().before(dt) || pp.getDateCreationProduct().equals(dt)))		    	
	    		newProds.add(pp);
	    	else if(pp.getDateEndNewProduct().equals(dt1) || pp.getDateEndNewProduct().after(dt1))
	    		newProds.remove(pp);
	    	
	    }
		
		return newProds;
	}
	
	public String readAndCheckImage(String file) throws FileNotFoundException, IOException, NotFoundException, ChecksumException, FormatException {
		
			InputStream barCodeInputStream = new FileInputStream("" + file);
			BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);

			LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			MultiFormatReader reader = new MultiFormatReader();
			com.google.zxing.Result result = reader.decode(bitmap);

			System.out.println("Barcode text is " + result.getText());
			String codeResult = result.getText();
			int numberCodeResult = convertThreeFirstCharsStringToInt(codeResult);
			if(numberCodeResult == 619)
				return codeResult;
			else
				return "";
	}

	@Override
	public void generateImageBarcode(int idProd, String image_name,String barcode){
		
		int threFirstBarcodeInt = convertThreeFirstCharsStringToInt(barcode);
		
		try {
			if(barcode.length() == 13 && threFirstBarcodeInt == 619){
					
				Code128Bean code128 = new Code128Bean();
				code128.setHeight(15f);
				code128.setModuleWidth(0.3);
				code128.setQuietZone(10);
				code128.doQuietZone(true);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
				code128.generateBarcode(canvas, barcode);
				canvas.finish();
	
				//write to png file
				FileOutputStream fos = new FileOutputStream("/Users/wided/Desktop/GYT/PI4SAE/src/main/webapp/uploads/"+image_name);
				fos.write(baos.toByteArray());
				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	//normalment manesthakhech tetfasakh
	@Override
	public void generateImageBarcodee(String image_name,String barcode){
		
		int threFirstBarcodeInt = convertThreeFirstCharsStringToInt(barcode);
		
		try {
			if(barcode.length() == 13 && threFirstBarcodeInt == 619){
					
				Code128Bean code128 = new Code128Bean();
				code128.setHeight(15f);
				code128.setModuleWidth(0.3);
				code128.setQuietZone(10);
				code128.doQuietZone(true);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
				code128.generateBarcode(canvas, barcode);
				canvas.finish();
	
				//write to png file
				FileOutputStream fos = new FileOutputStream("./src/main/webapp/QRCodeGenerator/QRCode"+image_name+".png");
				fos.write(baos.toByteArray());
				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Override
	public byte[] getBarCodeImage(String text, int width, int height, String filePath) throws Exception{
			
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			Writer writer = new Code128Writer();
			BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);

			
			Path path = FileSystems.getDefault().getPath(filePath);
			
			FileOutputStream out = new FileOutputStream(filePath);
			MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
			
			System.out.println(out);
						
			
			return byteArrayOutputStream.toByteArray();
		}			
	
	public int convertThreeFirstCharsStringToInt(String str) {
		int number = 0;
		String firstThreeChars = "";
		try {
			if (str.length() > 3) {
				firstThreeChars = str.substring(0, 3);
			} else {
				firstThreeChars = str;
			}
			number = Integer.parseInt(firstThreeChars);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return number;
	}

	
	
	@Override
	public byte[] genrateAndDownloadQRCode(String text, int width, int height, String filePath) throws Exception{

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
		
		Path path = FileSystems.getDefault().getPath(filePath);
		
		FileOutputStream out = new FileOutputStream(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
		
		System.out.println(out);
		
		return byteArrayOutputStream.toByteArray();

	}
	
	@Override
	public List<Product> RecommendedProduct(int id) {
		Userr user = ((Userr)userrRepository.findById(id).get());
		AgeRecommandationProduct ageRec = AgeRecommandationProduct.CHILD;
				
		System.out.println(" user :"+user.toString());
		System.out.println(" ss :"+ageRec);
		
		if(user.getAge() <= 17){
			ageRec = AgeRecommandationProduct.CHILD;
		}
		else if (user.getAge() >= 18 && user.getAge() <= 29){
			ageRec = AgeRecommandationProduct.JUNIOR;
		}
		else {
			ageRec = AgeRecommandationProduct.SENIOR;
		}
		
		return productRepository.RecommendedProducts(user.getGender().name(),ageRec.name());
	}


	@Override
	public List<String> search(String keyword){
		return this.productRepository.search(keyword);
	}
	
	@Override
	public List<Product> searchDetails(String keyword){
		return this.productRepository.searchDetails(keyword);
	}
	
	@Override
	public List<Product> recherche(String keyword){
		return this.productRepository.rechercheProd(keyword);
	}
	

	@Override
	public List<Product> getAllProductsByCategory(int idCat) {
		Category category = categoryRepository.findById(idCat).get();
		List<Product> prod = new ArrayList<>();
		for (Product p : category.getProducts()) {
			prod.add(p);
		}
		
		return prod;
	}
	
	@Override
	public List<Product> getAllProductsByNameCategory(String nomCat) {

		List<Product> prod = new ArrayList<>();
		for (Product p : productRepository.findAll()) {
			if(p.getCategory().getNameCategory() == nomCat)
			prod.add(p);
		}
		
		return prod;
	}
	
	
	
	
	
	
	@Override
	public List<Product> getAllProductsRecommandedForMen() {

		return productRepository.getAllProductsRecommandedForMen();
	}
		
	@Override
	public List<Product> getAllProductsRecommandedForWemen() {

		return productRepository.getAllProductsRecommandedForWomen();
	}
	
	@Override
	public List<Product> getAllProductsRecommandedForChild() {

		return productRepository.getAllProductsRecommandedForChild();
	}
	
	@Override
	public List<Product> getAllProductsRecommandedForJunior() {

		return productRepository.getAllProductsRecommandedForJunior();
	}
	
	@Override
	public List<Product> getAllProductsRecommandedForSenior() {

		return productRepository.getAllProductsRecommandedForSenior();
	}
	
	@Override
	public float StatisticsMan(){
		
		float man = 0;
		float woman = 0;
		
		for(Product p: getAllProducts()){
			if(p.getGender() == GenderRecommandation.MAN){
				man = man + 1;
			}
			else if(p.getGender() == GenderRecommandation.WOMAN){
				woman = woman + 1;
			}
		}
		
		float tot = woman + man;
		float a = man/tot;
		
		return a*100;
	}
	
	@Override
	public float StatisticsWoman(){
		
		float man = 0;
		float woman = 0;
		
		for(Product p: getAllProducts()){
			if(p.getGender() == GenderRecommandation.MAN){
				man = man + 1;
			}
			else if(p.getGender() == GenderRecommandation.WOMAN){
				woman = woman + 1;
			}
		}
		
		float tot = woman + man;
		float a = woman/tot;
		
		return a*100;
	}
	
	
	@Override
	public float StatisticsChild(){
		
		float child = 0;
		float junior = 0;
		float senior = 0;
		
		for(Product p: getAllProducts()){
			if(p.getAge() == AgeRecommandationProduct.CHILD){
				child = child + 1;
			}
			else if(p.getAge() == AgeRecommandationProduct.JUNIOR){
				junior = junior + 1;
			}
			else if(p.getAge() == AgeRecommandationProduct.SENIOR){
				senior = senior + 1;
			}
		}
		
		float tot = child + junior + senior;
		float a = child/tot;
		
		return a*100;
	}
	
	@Override
	public float StatisticsJunior(){
		
		float child = 0;
		float junior = 0;
		float senior = 0;
		
		for(Product p: getAllProducts()){
			if(p.getAge() == AgeRecommandationProduct.CHILD){
				child = child + 1;
			}
			else if(p.getAge() == AgeRecommandationProduct.JUNIOR){
				junior = junior + 1;
			}
			else if(p.getAge() == AgeRecommandationProduct.SENIOR){
				senior = senior + 1;
			}
		}
		
		float tot = child + junior + senior;
		float a = junior/tot;
		
		return a*100;
	}
	
	@Override
	public float StatisticsSenior(){
		
		float child = 0;
		float junior = 0;
		float senior = 0;
		
		for(Product p: getAllProducts()){
			if(p.getAge() == AgeRecommandationProduct.CHILD){
				child = child + 1;
			}
			else if(p.getAge() == AgeRecommandationProduct.JUNIOR){
				junior = junior + 1;
			}
			else if(p.getAge() == AgeRecommandationProduct.SENIOR){
				senior = senior + 1;
			}
		}
		
		float tot = child + junior + senior;
		float a = senior/tot;
		
		return a*100;
	}
	
	
	

	
	
	
	
	
	
	
	
	@Override
	public List<Product> getTopProdsByPrice(int nbr) {

		List<Product> currentProds = (List<Product>) productRepository.findAll();
		List<Product> top5Prods = new ArrayList<>();
        
		int i = 0;
		Date d=new Date();  
        int year=d.getYear();  
        int currentYear=year+1900; 
        System.out.println(currentYear);


		do{
		
		Optional<Product> highestProduct=
				currentProds.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Product::getPriceProduct)));
				         
				Product highestPriceProd = highestProduct.get();
				top5Prods.add(highestPriceProd);
				currentProds.remove(highestPriceProd);
				i++;	
				
		} while(i < nbr); 
		
		
		List<Product> top5ProdsYear = new ArrayList<>();

		
		for (Product p : top5Prods) {
        	int d1 = p.getDateCreationProduct().getYear()+1900;
			if(d1 == currentYear){
				top5ProdsYear.add(p);
			}
		}

				return top5ProdsYear;
	}
	
	@Override
	public List<Product> whishList(int idProd){
		
		List<Product> whishList = new ArrayList<>();
		Product p = productRepository.findById(idProd).get();
		Category c = p.getCategory();
		Marque m = p.getMarque();
		
		for(Product pc: getAllProducts()){
			if(pc.getCategory() == c && pc.getMarque() == m){
				whishList.add(pc);
			}
		}
		
		whishList.remove(p);
		
		return whishList;	
	}


	
	
	
	
	
	
	
	
	

}
