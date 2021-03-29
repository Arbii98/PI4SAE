package aces.esprit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.Advertising;
import aces.esprit.entity.Category;

public interface AdvertisingRepository extends CrudRepository<Advertising, Integer>{
	
	@Query("select count(a.channelAd) from Advertising a")
	int getNbAdvertisings();

}
