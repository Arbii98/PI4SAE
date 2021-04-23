package aces.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Stock;


@Repository
public interface StockRepository extends CrudRepository<Stock,Integer> {

	@Query("Select s from Stock s where s.etat !='Epuise' order by s.dateAchat ASC")
	List<Stock> findAllOrderByDate();
}
