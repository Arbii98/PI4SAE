package aces.esprit.service;

import org.springframework.stereotype.Service;

import aces.esprit.entity.Event;
import aces.esprit.entity.Jackpot;
import aces.esprit.entity.JackpotEvolutionStat;
import aces.esprit.entity.JackpotType;
import aces.esprit.entity.TopDonatorsStat;
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
		float totalContributions = 0;
		float totalWidthdraw = 0;
		if(jr.getCountContributions()>0)
		{
			totalContributions = jr.GetSumContributions();
		}
		
		if(jr.getCountWithdraw()>0)
		{
			totalWidthdraw = jr.GetSumWithdraw();
		}
		
		return totalContributions-totalWidthdraw;
	}

	@Override
	public float getMontantTotal() {
		if(jr.getCountContributions()>0)
		{
			return jr.GetSumContributions();
		}
		else
		{
			return 0;
		}
	}

	@Override
	public List<JackpotEvolutionStat> GetJackpotEvolution() {
		return jr.GetJackpotEvolution();
	}

	@Override
	public List<TopDonatorsStat> GetTopDonators() {
		return jr.GetTopDonators();
	}
	
	
	
	
	
	
	
	
	

}
