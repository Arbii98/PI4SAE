package aces.esprit.service;

import java.util.Date;
import java.util.List;

import aces.esprit.entity.Advertising;

public interface IAdvertisingService {
	
	void addAdvertising(Advertising advertising);
	List<Advertising> getAllAdvertisings();
	Advertising getAdvertisingById(int id);
	void updateAdvertising(Advertising advertising, int idAd);
	void deleteAdvertisingById(int id);
	void deleteAllAdvertisings();

	int getnbrViewPerAd(int idAd, int idU);
	float getCostAdvertising(int idAd);
	float getStatisticsPricePerAd(int idAd);
	float getStatisticsDurationPerAd(int idAd);
	float getStatMan(int idAd);
	float getStatWomen(int idAd);
	float getPrixParEntrepStat();
	float getPrixParSocStat();

	
}
