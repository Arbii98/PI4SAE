package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Advertising;
import aces.esprit.service.IAdvertisingService;

@RestController
public class AdvertisingController {
	
	@Autowired
	IAdvertisingService iAdvertisingService;

	public AdvertisingController() {
	}

	public AdvertisingController(IAdvertisingService iAdvertisingService) {
		this.iAdvertisingService = iAdvertisingService;
	}
	
	/*
	 * http://localhost:8081/SpringMVC/servlet/addAdvertising
	 */
	/*
	{
    "idAd": 1,
    "channelAd": "a",
    "dateBeginAd": "2021-03-02",
    "dateEndAd": "2021-03-18",
    "nbrInitialViewsAd": 2,
    "nbrFinalViewsAd": 1,
    "priceAd": 12,
    "typeAd": "img"
	}
	 */
	@PostMapping("/addAdvertising")
	@ResponseBody
	public Advertising addAdvertising(@RequestBody Advertising advertising) {
		iAdvertisingService.addAdvertising(advertising);
		return advertising;
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getAdvertisingById/1
	 */
	@GetMapping(value = "getAdvertisingById/{idAdvertising}")
	@ResponseBody
	public Advertising getAdvertisingById(@PathVariable("idAdvertising") int idAd) {
		return iAdvertisingService.getAdvertisingById(idAd);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getAllAds
	 */
	@GetMapping(value = "/getAllAds")
	@ResponseBody
	public List<Advertising> getAllAdvertisings() {
		return iAdvertisingService.getAllAdvertisings();
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/updateAd/1
	 */
	@PutMapping(value = "updateAd/{idAdvertising}")
	public void updateAdvertising(@RequestBody Advertising advertising, @PathVariable int idAd) {
		iAdvertisingService.updateAdvertising(advertising, idAd);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/deleteAdvertisingById/1
	 */
	@DeleteMapping("/deleteAdvertisingById/{idAd}")
	@ResponseBody
	public void deleteAdvertisingById(@PathVariable("idAd") int idAd) {
		iAdvertisingService.deleteAdvertisingById(idAd);

	}
	
	/*@GetMapping(value = "/getNbrAds")
	@ResponseBody
	public int getNbProds() {
		return iAdvertisingService.getNbrAdvertising();
	}*/

	/*
	 * http://localhost:8081/SpringMVC/servlet/deleteAllAds
	 */
	@DeleteMapping("/deleteAllAds")
	@ResponseBody
	public void deleteAllAdvertisings() {
		iAdvertisingService.deleteAllAdvertisings();

	}
	
	@GetMapping(value = "nbr/{idAdvertising}/{idUser}")
	@ResponseBody
	public int nb(@PathVariable("idAdvertising") int idAd, @PathVariable("idUser") int idUser) {
		return iAdvertisingService.getnbrViewPerAd(idAd, idUser);
	}
	
	@GetMapping(value = "getCostAdvertising/{idAdvertising}")
	@ResponseBody
	public float nbrr(@PathVariable("idAdvertising") int idAd) {
		return iAdvertisingService.getCostAdvertising(idAd);
	}
	
	@GetMapping(value = "/getStat/{idAdvertising}")
	@ResponseBody
	public float getStatisticsPricePerAd(@PathVariable("idAdvertising") int idAd) {
		return iAdvertisingService.getStatisticsPricePerAd(idAd);
	}
	
	@GetMapping(value = "/getDays/{idAdvertising}")
	@ResponseBody
	public float getDays(@PathVariable("idAdvertising") int idAd) {
		return iAdvertisingService.getStatisticsDurationPerAd(idAd);
	}
	
	@GetMapping(value = "/getStatMan/{idAdvertising}")
	@ResponseBody
	public float getStatMan(@PathVariable("idAdvertising") int idAd) {
		return iAdvertisingService.getStatMan(idAd);
	}
	
	@GetMapping(value = "/getStatG")
	@ResponseBody
	public float getPrixParEntrepStat() {
		return iAdvertisingService.getPrixParEntrepStat();
	}
	
	@GetMapping(value = "/getStatG2")
	@ResponseBody
	public float getPrixParEntrepStat2() {
		return iAdvertisingService.getPrixParSocStat();
	}

}
