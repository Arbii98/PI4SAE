package aces.esprit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Product;
import aces.esprit.entity.Rayon;
import aces.esprit.repository.ProductRepository;
import aces.esprit.repository.RayonRepository;

@Service
public class RayonService {

	@Autowired
	RayonRepository rr;
	@Autowired
	ProductRepository pr;
	
	public Rayon addRayon(Rayon rayon)
	{
		rr.save(rayon);
		return rayon;
	}
	
	public List<Rayon> getRayons()
	{
	
		return (List<Rayon>) rr.findAll();
		
	}
	
	
	public List<Product> getProductsInRayon(int idRayon)
	{
		Rayon rayon = (Rayon) rr.findById(idRayon).orElse(null);
		return rayon.getProduits();
	}
	
	
	public void addProductToRayon(int idProduct, int idRayon)
	{
		Rayon rayon = (Rayon) rr.findById(idRayon).orElse(null);
		Product produit = (Product) pr.findById(idProduct).orElse(null);
		rayon.getProduits().add(produit);
		rr.save(rayon);
	}
	
	public void removeProductFromRayon(int idProduct, int idRayon)
	{
		Rayon rayon = (Rayon) rr.findById(idRayon).orElse(null);
		Product produit = (Product) pr.findById(idProduct).orElse(null);
		if(rayon.getProduits().contains(produit))
		{
			rayon.getProduits().remove(produit);
		}
	}
	
	public Rayon findRayonByProduct(int idProduct)
	{
		List<Rayon> myList = (List<Rayon>) rr.findAll();
		Product produit = (Product) pr.findById(idProduct).orElse(null);
		for(Rayon r: myList)
		{
			if(r.getProduits().contains(produit))
			{
				return r;
			}
		}
		return null;
	}
	
	public void deleteRayon(int idRayon)
	{
		Rayon rayon = (Rayon) rr.findById(idRayon).orElse(null);
		rr.delete(rayon);
	}
	
	
}
