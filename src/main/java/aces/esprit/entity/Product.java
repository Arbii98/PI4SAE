package aces.esprit.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;
	
	@Column(name = "name")
	private String nameProduct;
	
	@Column(name = "description")
	private String descriptionProduct;
	
	@Column(name = "price")
	private float priceProduct;
	
	@Column(name = "dateCreation")
	@Temporal(TemporalType.DATE)
	private Date dateCreationProduct;
	
	@Column(name = "dateEndNew")
	@Temporal(TemporalType.DATE)
	private Date dateEndNewProduct;
	
	@Column(name = "image")
	private String imageFileNameProduct;
	
	@Column(name = "imageBarcode")
	private String imageBarcodeFileNameProduct;
	
	@Column(name = "video")
	private String videoFileNameProduct;
	
	@Column(name = "barcode")
	private String barcodeProduct;
	
	@Column(name = "qrCodeImage")
	private String qrCodeImageProduct;
	
	@Column(name = "qr")
	private byte[] qr;
	
	@Column(name = "generateBarcode")
	private boolean generateBarcode;
	
	@Column(name = "age")
	@Enumerated(EnumType.STRING)
	private AgeRecommandationProduct age;
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private GenderRecommandation gender;
	
	@ManyToOne
	@JoinColumn(name = "fkIdCategory")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "fkIdAd")
	private Advertising advertising;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<LikeProduct> likeProducts;
	
	
	//@OneToOne(mappedBy="produit")
	@OneToOne
	private Stock currentStock;
	
	
	@ManyToOne
	private Rayon rayon;

	public Product() {
		super();
	}
	
	public Product(int id)
	{
		super();
		this.idProduct=id;
	}

	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}

	

	public Product(int idProduct, String nameProduct, String descriptionProduct, float priceProduct,
			Date dateCreationProduct, Date dateEndNewProduct, String imageFileNameProduct,
			String imageBarcodeFileNameProduct, String videoFileNameProduct, String barcodeProduct, Category category,
			Advertising advertising, List<LikeProduct> likeProducts, Rayon rayon) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.descriptionProduct = descriptionProduct;
		this.priceProduct = priceProduct;
		this.dateCreationProduct = dateCreationProduct;
		this.dateEndNewProduct = dateEndNewProduct;
		this.imageFileNameProduct = imageFileNameProduct;
		this.imageBarcodeFileNameProduct = imageBarcodeFileNameProduct;
		this.videoFileNameProduct = videoFileNameProduct;
		this.barcodeProduct = barcodeProduct;
		this.category = category;
		this.advertising = advertising;
		this.likeProducts = likeProducts;
		this.rayon = rayon;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", descriptionProduct="
				+ descriptionProduct + ", priceProduct=" + priceProduct + ", dateCreationProduct=" + dateCreationProduct
				+ ", imageFileNameProduct=" + imageFileNameProduct + ", videoFileNameProduct=" + videoFileNameProduct
				+ ", barcodeProduct=" + barcodeProduct + "]";
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getDescriptionProduct() {
		return descriptionProduct;
	}

	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}

	public float getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(float priceProduct) {
		this.priceProduct = priceProduct;
	}

	public Date getDateCreationProduct() {
		return dateCreationProduct;
	}

	public void setDateCreationProduct(Date dateCreationProduct) {
		this.dateCreationProduct = dateCreationProduct;
	}

	public String getImageFileNameProduct() {
		return imageFileNameProduct;
	}

	public void setImageFileNameProduct(String imageFileNameProduct) {
		this.imageFileNameProduct = imageFileNameProduct;
	}

	public String getVideoFileNameProduct() {
		return videoFileNameProduct;
	}

	public void setVideoFileNameProduct(String videoFileNameProduct) {
		this.videoFileNameProduct = videoFileNameProduct;
	}



	public String getBarcodeProduct() {
		return barcodeProduct;
	}

	public void setBarcodeProduct(String barcodeProduct) {
		this.barcodeProduct = barcodeProduct;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Advertising getAdvertising() {
		return advertising;
	}

	public void setAdvertising(Advertising advertising) {
		this.advertising = advertising;
	}

	public Stock getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Stock currentStock) {
		this.currentStock = currentStock;
	}

	
	public List<LikeProduct> getLikeProducts() {
		return likeProducts;
	}

	public void setLikeProducts(List<LikeProduct> likeProducts) {
		this.likeProducts = likeProducts;
	}

	public Date getDateEndNewProduct() {
		return dateEndNewProduct;
	}

	public void setDateEndNewProduct(Date dateEndNewProduct) {
		this.dateEndNewProduct = dateEndNewProduct;
	}

	public String getImageBarcodeFileNameProduct() {
		return imageBarcodeFileNameProduct;
	}

	public void setImageBarcodeFileNameProduct(String imageBarcodeFileNameProduct) {
		this.imageBarcodeFileNameProduct = imageBarcodeFileNameProduct;
	}

	public boolean isGenerateBarcode() {
		return generateBarcode;
	}

	public void setGenerateBarcode(boolean generateBarcode) {
		this.generateBarcode = generateBarcode;
	}

	public String getQrCodeImageProduct() {
		return qrCodeImageProduct;
	}

	public void setQrCodeImageProduct(String qrCodeImageProduct) {
		this.qrCodeImageProduct = qrCodeImageProduct;
	}

	public AgeRecommandationProduct getAge() {
		return age;
	}

	public void setAge(AgeRecommandationProduct age) {
		this.age = age;
	}

	public byte[] getQr() {
		return qr;
	}

	public void setQr(byte[] qr) {
		this.qr = qr;
	}

	
	
	

}
