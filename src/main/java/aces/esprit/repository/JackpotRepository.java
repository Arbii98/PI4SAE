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

@Repository
public interface JackpotRepository extends CrudRepository<Jackpot, Integer>{
	
	@Query("select sum(j.montant) from Jackpot j where j.type ='CONTRIBUTION'")
	int GetSumContributions();
	
	@Query("select sum(j.montant) from Jackpot j where j.type ='WITHDRAW'")
	int GetSumWithdraw();

	
	@Query(value="select cast(t1.date as datetime) ,"
			+ " SUM(case when t2.type = 'CONTRIBUTION' then t2.montant when t2.type = 'WITHDRAW' then -t2.montant end) as somme"
			+ " from jackpot t1 inner join jackpot t2 on t1.id >= t2.id"
			+ " group by t1.id, t1.montant, CAST(t1.date as date) order by t1.id",nativeQuery = true)
	List<Object[]> GetJackpotEvolution();
	
	@Query("select sum(j.montant) as somme, (j.client) from Jackpot j where j.type ='CONTRIBUTION' group by j.client order by somme asc ")
	List<Object[]> GetTopDonators();
}
