package aces.esprit.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class History {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	private String ip;
	
	@ManyToOne
	private Users user;
	
	private String countryShort;
	
	private String countryLong;
	
	private String region;
	
	private String city;
	
	private float latitude;
	
	private float longitude;
	
	private String zipCode;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getCountryShort() {
		return countryShort;
	}

	public void setCountryShort(String countryShort) {
		this.countryShort = countryShort;
	}

	public String getCountryLong() {
		return countryLong;
	}

	public void setCountryLong(String countryLong) {
		this.countryLong = countryLong;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		zipCode = zipCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public History() {
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public History(int id, String ip, Users user, String countryShort, String countryLong, String region, String city,
			float latitude, float longitude, String zipCode, Date date) {
		super();
		this.id = id;
		this.ip = ip;
		this.user = user;
		this.countryShort = countryShort;
		this.countryLong = countryLong;
		this.region = region;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zipCode = zipCode;
		this.date = date;
	}

	public History(String ip, Users user, String countryShort, String countryLong, String region, String city,
			float latitude, float longitude, String zipCode, Date date) {
		super();
		this.ip = ip;
		this.user = user;
		this.countryShort = countryShort;
		this.countryLong = countryLong;
		this.region = region;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zipCode = zipCode;
		this.date = date;
	}
	
	
	

}
