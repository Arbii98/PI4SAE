package aces.esprit.service;

import org.springframework.stereotype.Service;

import aces.esprit.entity.Event;
import aces.esprit.entity.Jackpot;
import aces.esprit.entity.JackpotType;
import aces.esprit.entity.User;
import aces.esprit.repository.EventRepository;
import aces.esprit.repository.JackpotRepository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class JackpotService implements IJackpotService{
	
	@Autowired
	JackpotRepository jr;
	@Autowired
	EventRepository er;

	@Override
	public Jackpot addContribution(Jackpot jackpot) {
		jackpot.setType(JackpotType.CONTRIBUTION);
		jackpot.setEvent(null);
		Date d = new Date();
		jackpot.setDate(d);
		
		jr.save(jackpot);
		
		return jackpot;
	}

	@Override
	public Jackpot addWithdraw(Jackpot jackpot) {
		jackpot.setType(JackpotType.WITHDRAW);
		jackpot.setClient(null);
		
		
		Event event = (Event) er.findById(jackpot.getEvent().getId()).orElse(null);
		jackpot.setEvent(event);
		
		jackpot.setMontant(event.getCout());
		
		Date d = new Date();
		jackpot.setDate(d);
		
		jr.save(jackpot);
		
		return jackpot;
	}

	@Override
	public float getCurrentMontantTotal() {
		return jr.GetSumContributions()-jr.GetSumWithdraw();
	}

	@Override
	public float getMontantTotal() {
		return jr.GetSumContributions();
	}

	@Override
	public List<Object[]> GetJackpotEvolution() {
		return jr.GetJackpotEvolution();
	}

	@Override
	public List<Object[]> GetTopDonators() {
		return jr.GetTopDonators();
	}
	
	
	
	
	
	
	
	
	

}
