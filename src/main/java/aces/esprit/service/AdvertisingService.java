package aces.esprit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Advertising;
import aces.esprit.entity.GenderUser;
import aces.esprit.entity.User;
import aces.esprit.repository.AdvertisingRepository;
import aces.esprit.repository.UserRepository;

@Service
public class AdvertisingService implements IAdvertisingService {

	@Autowired
	AdvertisingRepository advertisingRepository;
	
	@Autowired
	UserRepository userRepository;

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
		User user = userRepository.findById(idU).get();
		
		int a = 0;
		if(user.getGender() == GenderUser.MAN){
			a = a + 1;
			a = ad.getNbrFinalViewsAd() + 1;
			ad.setNbrFinalViewsAd(a);
		}
		
		
		advertisingRepository.save(ad);
		return a;
	}

}
