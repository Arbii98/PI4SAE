package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Advertising;

public interface IAdvertisingService {
	
	void addAdvertising(Advertising advertising);
	List<Advertising> getAllAdvertisings();
	Advertising getAdvertisingById(int id);
	void updateAdvertising(Advertising advertising, int idAd);
	void deleteAdvertisingById(int id);
	void deleteAllAdvertisings();

}