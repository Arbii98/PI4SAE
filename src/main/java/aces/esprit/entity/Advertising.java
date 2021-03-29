package aces.esprit.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Advertising implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAd;
	
	@Column(name = "channel")
	private String channelAd; 
	
	@Column(name = "sponsorType")
	@Enumerated(EnumType.STRING)
	private SponsorType sponsorType; 
	
	@Column(name = "dateBegin")
	@Temporal(TemporalType.DATE)
	private Date dateBeginAd;
	
	@Column(name = "dateEnd")
	@Temporal(TemporalType.DATE)
	private Date dateEndAd;
	
	@Column(name = "initialViewsNumber")
	private int nbrInitialViewsAd;
	
	@Column(name = "finalViewsNumber")
	private int nbrFinalViewsAd;
	
	@Column(name = "priceDay")
	private float priceAdPerDay;
	
	@Column(name = "priceView")
	private float priceAdPerView;
	
	@Column(name = "imagePrice")
	private float imagePriceAd;
	
	@Column(name = "videoPrice")
	private float videoPriceAd;
	
	@Column(name = "type")
	private String typeAd;
	
	@JsonIgnore
	@OneToMany(mappedBy = "advertising", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private List<Product> products;

	public Advertising() {
		super();
	}

	public Advertising(int idAd, String channelAd, SponsorType sponsorType, Date dateBeginAd, Date dateEndAd,
			int nbrInitialViewsAd, int nbrFinalViewsAd, float priceAdPerDay, float priceAdPerView, float imagePriceAd,
			float videoPriceAd, String typeAd, List<Product> products) {
		super();
		this.idAd = idAd;
		this.channelAd = channelAd;
		this.sponsorType = sponsorType;
		this.dateBeginAd = dateBeginAd;
		this.dateEndAd = dateEndAd;
		this.nbrInitialViewsAd = nbrInitialViewsAd;
		this.nbrFinalViewsAd = nbrFinalViewsAd;
		this.priceAdPerDay = priceAdPerDay;
		this.priceAdPerView = priceAdPerView;
		this.imagePriceAd = imagePriceAd;
		this.videoPriceAd = videoPriceAd;
		this.typeAd = typeAd;
		this.products = products;
	}

	public int getIdAd() {
		return idAd;
	}

	public void setIdAd(int idAd) {
		this.idAd = idAd;
	}

	public String getChannelAd() {
		return channelAd;
	}

	public void setChannelAd(String channelAd) {
		this.channelAd = channelAd;
	}

	public Date getDateBeginAd() {
		return dateBeginAd;
	}

	public void setDateBeginAd(Date dateBeginAd) {
		this.dateBeginAd = dateBeginAd;
	}

	public Date getDateEndAd() {
		return dateEndAd;
	}

	public void setDateEndAd(Date dateEndAd) {
		this.dateEndAd = dateEndAd;
	}

	public int getNbrInitialViewsAd() {
		return nbrInitialViewsAd;
	}

	public void setNbrInitialViewsAd(int nbrInitialViewsAd) {
		this.nbrInitialViewsAd = nbrInitialViewsAd;
	}

	public int getNbrFinalViewsAd() {
		return nbrFinalViewsAd;
	}

	public void setNbrFinalViewsAd(int nbrFinalViewsAd) {
		this.nbrFinalViewsAd = nbrFinalViewsAd;
	}

	public String getTypeAd() {
		return typeAd;
	}

	public void setTypeAd(String typeAd) {
		this.typeAd = typeAd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public float getImagePriceAd() {
		return imagePriceAd;
	}

	public void setImagePriceAd(float imagePriceAd) {
		this.imagePriceAd = imagePriceAd;
	}

	public float getVideoPriceAd() {
		return videoPriceAd;
	}

	public void setVideoPriceAd(float videoPriceAd) {
		this.videoPriceAd = videoPriceAd;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public SponsorType getSponsorType() {
		return sponsorType;
	}

	public void setSponsorType(SponsorType sponsorType) {
		this.sponsorType = sponsorType;
	}

	public float getPriceAdPerDay() {
		return priceAdPerDay;
	}

	public void setPriceAdPerDay(float priceAdPerDay) {
		this.priceAdPerDay = priceAdPerDay;
	}

	public float getPriceAdPerView() {
		return priceAdPerView;
	}

	public void setPriceAdPerView(float priceAdPerView) {
		this.priceAdPerView = priceAdPerView;
	}
	
	

	

}
