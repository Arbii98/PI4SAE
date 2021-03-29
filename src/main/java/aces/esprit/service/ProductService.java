package aces.esprit.service;

import java.io.FileInputStream;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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

import org.springframework.util.StringUtils;
import java.util.Base64;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import aces.esprit.entity.LikeProduct;
import aces.esprit.entity.LikedProduct;
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
	public Product addNewProduct(int idProd, String name,String imgNameBarcode, String barcode, boolean generateBarcode) throws Exception {
		
		Product p = productRepository.findById(idProd).get();

		p.setImageBarcodeFileNameProduct(imgNameBarcode);
		
		Date today = new Date();
	    Calendar c1 = Calendar.getInstance(); 
	    c1.setTime(today); 
	    today = c1.getTime();
	    p.setDateCreationProduct(today);
	    
	    p.setGenerateBarcode(generateBarcode);
	    p.setBarcodeProduct(barcode);
	    	    
	    Date remainingDate = new Date();
	    Calendar c = Calendar.getInstance(); 
	    c.setTime(remainingDate); 
	    c.add(Calendar.DATE, 7);
	    remainingDate = c.getTime();
	    p.setDateEndNewProduct(remainingDate);
	    p.setNameProduct(name);
	    	            
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

	//check ken tekhdem wale
	public List<String> getAllProductsNamesByCategory(int idCat) {
		Category category = categoryRepository.findById(idCat).get();
		List<String> prodNames = new ArrayList<>();
		for (Product prod : category.getProducts()) {
			prodNames.add(prod.getNameProduct());
		}
		return prodNames;
	}

<<<<<<< Updated upstream
	
=======
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
	    	if(pp.getDateEndNewProduct().equals(dt1) || pp.getDateEndNewProduct().after(dt1))
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
		
		if(user.getAge() <= 15){
			ageRec = AgeRecommandationProduct.CHILD;
		}
		else if (user.getAge() >= 18 && user.getAge() <= 30){
			ageRec = AgeRecommandationProduct.JUNIOR;
		}
		else {
			ageRec = AgeRecommandationProduct.SENIOR;
		}
		
		return productRepository.RecommendedProducts(user.getGender().name(),ageRec.name());
	}



>>>>>>> Stashed changes

}
