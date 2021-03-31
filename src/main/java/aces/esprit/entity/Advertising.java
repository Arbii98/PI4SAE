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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name = "viewsMan")
	private int nbrManViewsAd;
	
	@Column(name = "viewsWomen")
	private int nbrWomenViewsAd;
	
	@Column(name = "finalViewsNumber")
	private int nbrFinalViewsAd;
	
	@Column(name = "priceDay")
	private float priceAdPerDay;
	
	@Column(name = "priceView")
	private float priceAdPerView;
	
	@ManyToOne
	@JoinColumn(name = "fkIdProduct")
	private Product product;

	public Advertising() {
		super();
	}

	public Advertising(int idAd, String channelAd, SponsorType sponsorType, Date dateBeginAd, Date dateEndAd,
			int nbrInitialViewsAd, int nbrFinalViewsAd, float priceAdPerDay, float priceAdPerView, float imagePriceAd,
			float videoPriceAd, String typeAd) {
		super();
		this.idAd = idAd;
		this.channelAd = channelAd;
		this.sponsorType = sponsorType;
		this.dateBeginAd = dateBeginAd;
		this.dateEndAd = dateEndAd;
		this.nbrFinalViewsAd = nbrFinalViewsAd;
		this.priceAdPerDay = priceAdPerDay;
		this.priceAdPerView = priceAdPerView;

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

	public int getNbrFinalViewsAd() {
		return nbrFinalViewsAd;
	}

	public void setNbrFinalViewsAd(int nbrFinalViewsAd) {
		this.nbrFinalViewsAd = nbrFinalViewsAd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public int getNbrManViewsAd() {
		return nbrManViewsAd;
	}

	public void setNbrManViewsAd(int nbrManViewsAd) {
		this.nbrManViewsAd = nbrManViewsAd;
	}

	public int getNbrWomenViewsAd() {
		return nbrWomenViewsAd;
	}

	public void setNbrWomenViewsAd(int nbrWomenViewsAd) {
		this.nbrWomenViewsAd = nbrWomenViewsAd;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

	

}
