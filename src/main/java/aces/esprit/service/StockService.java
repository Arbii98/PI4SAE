package aces.esprit.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Stock;
import aces.esprit.entity.StockStatus;
import aces.esprit.entity.Product;
import aces.esprit.repository.ProductRepository;
import aces.esprit.repository.StockRepository;
import java.util.List;


@Service
public class StockService {

	@Autowired
	StockRepository sr;
	
	@Autowired
	ProductRepository pr;
	
	@Autowired 
	CartService cs;
	
	@Autowired
	EmailService es;
	
	
	//Verified
	public void UnaffectProductStock(int idProduct)
	{
		Product produit = (Product) pr.findById(idProduct).orElse(null);
		Stock stock = (Stock) sr.findById(produit.getCurrentStock().getId()).orElse(null);
		produit.setCurrentStock(null);
		pr.save(produit);
		
		stock.setEtat(StockStatus.EnAttente);
		sr.save(stock);
	}
	
	
	//Verified
	public void AffectProductStock(int idProduit, int idStock)
	{
		Product produit = (Product) pr.findById(idProduit).orElse(null);
		if(produit.getCurrentStock()!=null)
		{
			Stock oldStock = (Stock) sr.findById(produit.getCurrentStock().getId()).orElse(null);
			if(oldStock != null)
			{
				oldStock.setEtat(StockStatus.EnAttente);
				sr.save(oldStock);
			}
		}
		
		Stock newStock = (Stock) sr.findById(idStock).orElse(null);
		newStock.setEtat(StockStatus.EnCours);
		produit.setCurrentStock(newStock);
		produit.setPriceProduct(newStock.getPrixVente());
		
		sr.save(newStock);
		pr.save(produit);
		
	}
	
	
	//Verified
	public void AffectStockToNewProduct(Product produit, Stock newStock)
	{
		newStock.setEtat(StockStatus.EnCours);
		produit.setCurrentStock(newStock);
		produit.setPriceProduct(newStock.getPrixVente());
		
		sr.save(newStock);
		pr.save(produit);
		
	}
	
	
	
	//Verified
	public Stock addStock(Stock stock)
	{
		Date d = new Date();
		stock.setDateAchat(d);
		stock.setQuantiteVendu(0);
		stock.setEtat(StockStatus.EnAttente);
		sr.save(stock);
		
		Product produit = (Product) pr.findById(stock.getProduit().getIdProduct()).orElse(null);
		
		if(produit.getCurrentStock()==null)
		{
			AffectStockToNewProduct(produit,stock);
		}
		
		
		
		return stock;
	}
	
	
	//Verified
	public int getProductStock(int id) {
		Product produit = (Product) pr.findById(id).orElse(null);
		if(produit.getCurrentStock()==null)
		{
			return 0;
		}
		else
		{
			return produit.getCurrentStock().getId();
		}
		
	}
	
	public int getQuantiteRestante(int id)
	{
		Stock stock = (Stock) sr.findById(id).orElse(null);
		int quantiteRestante=(int) (stock.getQuantite()-stock.getQuantiteVendu());
		return quantiteRestante;
	}
	
	public void deleteStock(int id)
	{
		Stock stock = (Stock) sr.findById(id).orElse(null);
		Product produit = (Product) pr.findById(stock.getProduit().getIdProduct()).orElse(null);
		if(produit.getCurrentStock().getId()==id)
		{
			produit.setCurrentStock(null);
			pr.save(produit);
		}
		sr.deleteById(id);
		
	}
	
	
	public boolean checkStock(int id, float quantite)
	{
		Stock stock = (Stock) sr.findById(id).orElse(null);
		float quantiteRestante = stock.getQuantite()-stock.getQuantiteVendu();
		if(quantiteRestante>quantite)
		{
			return true;
		}
		return false;
	}
	
	public List<Stock>findAllOrderByDate()
	{
		return sr.findAllOrderByDate();
	}
	
	
	
	public int decrementStock(int id, float quantite)
	{
		Stock stock = (Stock) sr.findById(id).orElse(null);
		if(checkStock(id,quantite))
		{
			stock.setQuantiteVendu(stock.getQuantiteVendu()+quantite);
			int quantiteRestante=getQuantiteRestante(stock.getId());
			cs.deleteBiggerThanStock(quantiteRestante,stock.getProduit());
			return 1;
		}
		else
		{
			stock.setQuantiteVendu(stock.getQuantiteVendu()+quantite);
			stock.setEtat(StockStatus.Epuise);
			sr.save(stock);
			List<Stock> mesStocks = sr.findAllOrderByDate();
			
			if(mesStocks.size()==0)
			{
				//NOMORESTOCK
				Product p = (Product) pr.findById(stock.getProduit().getIdProduct()).orElse(null);
				p.setCurrentStock(null);
				pr.save(p);
				System.out.println("NO MORE STOCK");
				//ENVOIE MAIL NO MORE STOCK
				es.sendMailStock("ahmed.oueslati1@esprit.tn","Stock");
				return -1;
			}
			else
			{
				
				Stock newStock = (Stock) mesStocks.get(0);
				Product p = (Product) pr.findById(stock.getProduit().getIdProduct()).orElse(null);
				AffectProductStock(p.getIdProduct(),newStock.getId());
				stock.setEtat(StockStatus.Epuise);
				sr.save(stock);
				return 0;
				
			}
		}
		
	}
}
