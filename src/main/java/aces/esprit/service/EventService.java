package aces.esprit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Event;
import aces.esprit.entity.Jackpot;
import aces.esprit.repository.EventRepository;

@Service
public class EventService implements IEventService {

	@Autowired
	EventRepository er;
	
	@Autowired
	JackpotService js;
	
	@Override
	public Event addEvent(Event event) {
		if(js.getCurrentMontantTotal()>event.getCout())
		{
			er.save(event);
			Jackpot j = new Jackpot();
			j.setEvent(event);
			js.addWithdraw(j);
			return event;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public List<Event> getAllEvents() {
		return (List<Event>) er.findAll();
	}

	@Override
	public List<Event> getUpcomingEvents() {
		return er.getUpcomingEvents();
	}

	@Override
	public void deleteEvent(int id) {
		er.deleteById(id);
	}
	
	

}
