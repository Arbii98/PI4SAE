package aces.esprit.service;

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
	@Override
	public int getnbrViewHomme(int idAd, int idU){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		Userr user = userrRepository.findById(idU).get();
		
		int a = 0;
		if(user.getGender() == GenderUser.MAN){
			a = a + 1;
			a = ad.getNbrFinalViewsAd() + 1;
			ad.setNbrFinalViewsAd(a);
		}
		
		
		advertisingRepository.save(ad);
		return a;
	}
	
	@Override
	public int getNbrAdvertising() {
		return advertisingRepository.getNbAdvertisings();
	}
	
	//new
	//click counter
	@Override
	public int getnbrViewPerAd(int idAd, int idU){
		
		Advertising ad = advertisingRepository.findById(idAd).get();
		Userr user = userrRepository.findById(idU).get();
		
		int allViews = 0;
	
		allViews = allViews + 1;
		allViews = ad.getNbrFinalViewsAd() + 1;
		ad.setNbrFinalViewsAd(allViews);
		advertisingRepository.save(ad);
		
		return allViews;
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
		int nbrVue = ad.getNbrInitialViewsAd();
		Date d1 = ad.getDateBeginAd();
		Date d2 = ad.getDateEndAd();
		long numberDays = getNbrDaysBetweenTwoDates(d1,d2);
		SponsorType typeSponsor = ad.getSponsorType();
		
		if(typeSponsor == SponsorType.SOCIETY)
			return ((prixParJour*numberDays)+(prixParVue*nbrVue))*2;
		else 
			return (prixParJour*numberDays)+(prixParVue*nbrVue);
		
	}

	


}
