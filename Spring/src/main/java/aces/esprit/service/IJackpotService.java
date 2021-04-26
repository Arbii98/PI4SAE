package aces.esprit.service;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

import aces.esprit.entity.Jackpot;

public interface IJackpotService {
	
	Jackpot addContribution(Jackpot jackpot);
	Jackpot addWithdraw(Jackpot jackpot);
	float getCurrentMontantTotal();
	float getMontantTotal();
	
	List<Object[]> GetJackpotEvolution();
	List<Object[]> GetTopDonators();

}
