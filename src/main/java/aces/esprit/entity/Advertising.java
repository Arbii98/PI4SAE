package aces.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "price")
	private float priceAd;
	
	@Column(name = "type")
	private String typeAd;
	
	@JsonIgnore
	@OneToMany(mappedBy = "advertising", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private List<Product> products;

	public Advertising() {
		super();
	}

	public Advertising(int idAd, String channelAd, Date dateBeginAd, Date dateEndAd, int nbrInitialViewsAd,
			int nbrFinalViewsAd, float priceAd, String typeAd) {
		super();
		this.idAd = idAd;
		this.channelAd = channelAd;
		this.dateBeginAd = dateBeginAd;
		this.dateEndAd = dateEndAd;
		this.nbrInitialViewsAd = nbrInitialViewsAd;
		this.nbrFinalViewsAd = nbrFinalViewsAd;
		this.priceAd = priceAd;
		this.typeAd = typeAd;
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

	public float getPriceAd() {
		return priceAd;
	}

	public void setPriceAd(float priceAd) {
		this.priceAd = priceAd;
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
	
	
	
	

	

}
