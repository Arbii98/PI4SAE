package aces.esprit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Event;


@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
	
	@Query("select e from Event e where e.dateEvent> CURRENT_DATE")
	List<Event> getUpcomingEvents();

}
