package aces.esprit.repository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Jackpot;
import aces.esprit.entity.JackpotEvolutionStat;
import aces.esprit.entity.TopDonatorsStat;

@Repository
public interface JackpotRepository extends CrudRepository<Jackpot, Integer>{
	
	@Query("select sum(j.montant) from Jackpot j where j.type ='CONTRIBUTION'")
	int GetSumContributions();
	
	@Query("select sum(j.montant) from Jackpot j where j.type ='WITHDRAW'")
	int GetSumWithdraw();
	
	@Query("select count(j) from Jackpot j where j.type = 'WITHDRAW'")
	int getCountWithdraw();
	
	@Query("select count(j) from Jackpot j where j.type = 'CONTRIBUTION'")
	int getCountContributions();

	
	@Query(value="select cast(t1.date as datetime) as date ,"
			+ " SUM(case when t2.type = 'CONTRIBUTION' then t2.montant when t2.type = 'WITHDRAW' then -t2.montant end) as montant"
			+ " from jackpot t1 inner join jackpot t2 on t1.id >= t2.id"
			+ " group by t1.id, t1.montant, CAST(t1.date as date) order by t1.id",nativeQuery = true)
	List<JackpotEvolutionStat> GetJackpotEvolution();
	
	@Query("select sum(j.montant) as montant, (j.client) as client from Jackpot j where j.type ='CONTRIBUTION' group by j.client order by montant asc ")
	List<TopDonatorsStat> GetTopDonators();
}
