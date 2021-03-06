package aces.esprit.service;

import java.awt.event.AdjustmentEvent;

import java.util.Date;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Advertising;
import aces.esprit.entity.GenderUser;
import aces.esprit.entity.User;
import aces.esprit.repository.AdvertisingRepository;
import aces.esprit.repository.UserRepository;
import aces.esprit.entity.LikeProduct;
import aces.esprit.entity.Product;
import aces.esprit.entity.SponsorType;
import aces.esprit.entity.Userr;
import aces.esprit.repository.AdvertisingRepository;
import aces.esprit.repository.UserrRepository;

@Service
public class AdvertisingService implements IAdvertisingService {

	@Autowired
	AdvertisingRepository advertisingRepository;
	
	@Autowired
	UserrRepository userrRepository;

	public AdvertisingService() {
	}

	public AdvertisingService(AdvertisingRepository advertisingRepository) {
		this.advertisingRepository = advertisingRepository;
	}

	@Override
	public void addAdvertising(Advertising advertising) {
		advertisingRepository.save(advertising);
	}

	@Override
	public List<Advertising> getAllAdvertisings() {
		return (List<Advertising>) advertisingRepository.findAll();

	}

	@Override
	public Advertising getAdvertisingById(int id) {
		return advertisingRepository.findById(id).get();
	}

	@Override
	public void updateAdvertising(Advertising advertising, int idAd) {
		if (advertisingRepository.findById(idAd).orElse(null) != null)
			advertising.setIdAd(idAd);
		advertisingRepository.save(advertising);
	}

	@Override
	public void deleteAdvertisingById(int id) {
		advertisingRepository.deleteById(id);

	}

	@Override
	public void deleteAllAdvertisings() {
		advertisingRepository.deleteAll();

	}
	
	//a enlever
	/*@Override
	public int getnbrViewMen(int idAd, int idU){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		Userr user = userrRepository.findById(idU).get();
		
		int man = 0;
		if(user.getGender() == GenderUser.MAN){
			man = man + 1;
			man = ad.getNbrManViewsAd() + 1;
			ad.setNbrManViewsAd(man);
		}
		advertisingRepository.save(ad);
		return man;
	}
	
	@Override
	public int getnbrViewWemen(int idAd, int idU){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		Userr user = userrRepository.findById(idU).get();
		
		int women = 0;
		if(user.getGender() == GenderUser.MAN){
			women = women + 1;
			women = ad.getNbrWomenViewsAd() + 1;
			ad.setNbrWomenViewsAd(women);
		}
		advertisingRepository.save(ad);
		return women;
	}
	
	@Override
	public int getNbrAdvertising() {
		return advertisingRepository.getNbAdvertisings();
	}*/
	
	//new
	//click counter
	@Override
	public int getnbrViewPerAd(int idAd, int idU){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		Userr user = userrRepository.findById(idU).get();
		
		int man = 0;
		int women = 0;
		/*int allViews = 0;
		
		allViews = allViews + 1;
		allViews = ad.getNbrFinalViewsAd() + 1;
		ad.setNbrFinalViewsAd(allViews);*/
		
		if(user.getGender() == GenderUser.MAN){
			man = man + 1;
			man = ad.getNbrManViewsAd() + 1;
			ad.setNbrManViewsAd(man);
		}
		else if(user.getGender() == GenderUser.WOMAN){
			women = women + 1;
			women = ad.getNbrWomenViewsAd() + 1;
			ad.setNbrWomenViewsAd(women);
		}

		advertisingRepository.save(ad);
		
		//allviews
		return man+women;
	}
	
	public long getNbrDaysBetweenTwoDates(Date d2, Date d1){
								
		long diff = d2.getTime() - d1.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		return diffDays;
	}
	
	@Override
	public float getCostAdvertising(int idAd){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		
		float prixParJour = ad.getPriceAdPerDay();
		float prixParVue = ad.getPriceAdPerView();
		int nbrVue = ad.getNbrFinalViewsAd();
		Date d1 = ad.getDateBeginAd();
		Date d2 = ad.getDateEndAd();
		long numberDays = getNbrDaysBetweenTwoDates(d2,d1);
		SponsorType typeSponsor = ad.getSponsorType();

		if(typeSponsor == SponsorType.SOCIETY)

			return ((prixParJour*numberDays)+(prixParVue*nbrVue))*2;
		
		else 
			return (prixParJour*numberDays)+(prixParVue*nbrVue);
		
	}
	
	public float getCostAllAds(){
		
		float result = 0f;
		
		for(Advertising ad: getAllAdvertisings()){
			result = result + getCostAdvertising(ad.getIdAd());
		}
		System.out.println("AAAAAAAAAA     "+result);
		return result;
		
	}
	

	@Override
	public float getStatisticsPricePerAd(int idAd){
	
		return (getCostAdvertising(idAd)/getCostAllAds())*100f;
		
	}
	
	public long getDurationOfAllAdsPerDays(){
		
		long result = 0;
		
		for(Advertising ad: getAllAdvertisings()){
			result = result + getNbrDaysBetweenTwoDates(ad.getDateEndAd(), ad.getDateBeginAd());
		}
		System.out.println("DDDDDDDDD   i  "+result);
		return result;
		
	}
	
	@Override
	public float getStatisticsDurationPerAd(int idAd){
	
		Advertising ad = advertisingRepository.findById(idAd).get();
		
		float nbr = getNbrDaysBetweenTwoDates(ad.getDateEndAd(), ad.getDateBeginAd());
		System.out.println("testtttt         "+nbr);
		
		
		float a = getDurationOfAllAdsPerDays();
		System.out.println("eeeee      "+a);
		
		float res = nbr/a;
		System.out.println("rrrrrr    "+res);
		
		return res;
		
	}

	@Override
	public float getStatMan(int idAd){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		
		float nbrViewHomme = ad.getNbrManViewsAd();
		float nbrViewAll = ad.getNbrWomenViewsAd();
		
		return (nbrViewHomme/nbrViewAll)*100;
		
	}
	
	@Override
	public float getStatWomen(int idAd){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		
		float nbrViewWomen = ad.getNbrWomenViewsAd();
		float nbrViewAll = ad.getNbrWomenViewsAd();
		
		return (nbrViewWomen/nbrViewAll)*100;
		
	}
	

	@Override
	public float getPrixParEntrepStat(){
		
		float prix = 0f;
		float prix2 = 0f;

		for(Advertising ad: getAllAdvertisings()){
			if(ad.getSponsorType() == SponsorType.ENTREPRENEUR){
				prix = prix + getCostAdvertising(ad.getIdAd());
			}
			else if (ad.getSponsorType() == SponsorType.SOCIETY){
				prix2 = prix2 + getCostAdvertising(ad.getIdAd());
			}
		}
		
		
		/*for(Advertising ad: getAllAdvertisings()){
			if(ad.getSponsorType() == SponsorType.SOCIETY){
				prix2 = prix2 + getCostAdvertising(ad.getIdAd());
			}
		}*/
		
		float tot = prix2+prix;
		
		float a = prix/tot;
		System.out.println("                        "+a);
		return a*100;
	}
	
	@Override
	public float getPrixParSocStat(){
		
		float prix = 0f;
		
		for(Advertising ad: getAllAdvertisings()){
			if(ad.getSponsorType() == SponsorType.ENTREPRENEUR){
				prix = prix + getCostAdvertising(ad.getIdAd());
			}
		}
		
		float prix2 = 0f;
		
		for(Advertising ad: getAllAdvertisings()){
			if(ad.getSponsorType() == SponsorType.SOCIETY){
				prix2 = prix2 + getCostAdvertising(ad.getIdAd());
			}
		}
		
		float tot = prix2+prix;
		float a = prix2/tot;

		System.out.println("aaa       "+tot);
		
		return a*100;
	}


	
	


}
