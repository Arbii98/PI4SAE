package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.*;
import aces.esprit.service.*;

@RestController
public class StockController {
	
	@Autowired
	StockService ss;
	
	@PostMapping("/addStock")
	@ResponseBody
	public Stock addStock(@RequestBody Stock stock) {
		return ss.addStock(stock);
		
	}
	
	
	@GetMapping("/GetProductStock/{idProduct}")
	@ResponseBody
	public int getProductStock(@PathVariable("idProduct") int idProduct)
	{
		return ss.getProductStock(idProduct);
	}
	
	@PutMapping("/UnaffectProductStock/{idProduct}")
	@ResponseBody
	public void UnaffectProductStock(@PathVariable("idProduct") int idProduct)
	{
		ss.UnaffectProductStock(idProduct);
	}
	
	@PutMapping("/AffectProductStock/{idProduct}/{idStock}")
	@ResponseBody
	public void AffectProductStock(@PathVariable("idProduct") int idProduct,@PathVariable("idStock") int idStock)
	{
		ss.AffectProductStock(idProduct,idStock);
	}
	
	@DeleteMapping("/DeleteStock/{idStock}")
	@ResponseBody
	public void DeleteStock(@PathVariable("idStock") int idStock)
	{
		ss.deleteStock(idStock);
	}
	
	@GetMapping("/GetNextStocks")
	@ResponseBody
	public int getNextStocks()
	{
		return ss.findAllOrderByDate().size();
	}
}
