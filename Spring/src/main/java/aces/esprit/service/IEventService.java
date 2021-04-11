package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Event;

public interface IEventService {
	
	Event addEvent(Event event);
	List<Event> getAllEvents();
	List<Event> getUpcomingEvents();
	void deleteEvent(int id);
	boolean getEventsToday();

}
