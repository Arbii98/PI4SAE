package aces.esprit.service;

import aces.esprit.entity.Delivery;
import aces.esprit.entity.DeliveryStatus;
import aces.esprit.entity.Commande;
import aces.esprit.repository.DeliveryRepository;
import aces.esprit.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;


import java.util.List;


@Service
public class DeliveryService {
	
	@Autowired
	DeliveryRepository dr;
	
	@Autowired
	CommandeRepository cr;
	
	
	public Delivery addDelivery(Delivery d) {
		Commande c = (Commande)cr.findById(d.getCommande().getId()).orElse(null);
		d.setCommande(c);
		d.setEtat(DeliveryStatus.enAttente);
		dr.save(d);
		return d;
		
	}
	
	public void deleteDelivery(int idDelivery) {
		dr.deleteById(idDelivery);
		
		
	}
	
	public void marquerEnAttente(int idDelivery) {
		Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
		d.setEtat(DeliveryStatus.enAttente);
		dr.save(d);
	}
	
	
	public void marquerEnCours(int idDelivery) {
		Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
		d.setEtat(DeliveryStatus.enCours);
		dr.save(d);
	}
	
	
	
	
	public void marquerDone(int idDelivery) {
		Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
		Date date = new Date();
		d.setDeliveryDate(date);
		d.setEtat(DeliveryStatus.done);
		dr.save(d);
	}
	
	
	public List<Delivery> getCurrentDeliveriesForDeliveryMan(int idDeliveryMan)
	{
		return dr.getCurrentDeliveriesForDeliveryMan(DeliveryStatus.done,idDeliveryMan);
	}
	
	public List<Delivery> getCurrentDeliveries()
	{
		return dr.getCurrentDeliveries(DeliveryStatus.done);
	}
	
	public List<Delivery> getHistoryDeliveries()
	{
		return dr.getHistoryDeliveries(DeliveryStatus.done);
	}
	
	public long getTempsAttenteDelivery(int idDelivery)
	{
		Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
		long duree = Math.abs(d.getDeliveryDate().getTime()-d.getCommande().getDateOrder().getTime());
		
		return duree;
	}
	
	public long getTempsAttenteMoyen()
	{
		long dureeTotale=0;
		for(Delivery d: getHistoryDeliveries() )
		{
			dureeTotale+=  Math.abs(d.getDeliveryDate().getTime()-d.getCommande().getDateOrder().getTime());
		}
		long moyenne=dureeTotale/getHistoryDeliveries().size();
		
		return moyenne;
	}
	
	

}
