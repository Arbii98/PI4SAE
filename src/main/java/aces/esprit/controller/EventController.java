package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Event;
import aces.esprit.service.EventService;

@RestController
public class EventController {
	@Autowired
	EventService es;
	
	@PostMapping("/addEvent")
	@ResponseBody
	public Event addEvent(@RequestBody Event event) {
		return es.addEvent(event);
		
	}
	
	@GetMapping(value = "/getAllEvents")
	@ResponseBody
	public List<Event> getAllEvents() {
		return es.getAllEvents();
	}
	
	@GetMapping(value = "/getUpcomingEvents")
	@ResponseBody
	public List<Event> getUpcomingEvents() {
		return es.getUpcomingEvents();
	}
	
	@DeleteMapping("/deleteEvent/{idEvent}")
	@ResponseBody
	public void deleteEvent(@PathVariable("idEvent") int idEvent) {
		es.deleteEvent(idEvent);
	}
	

}
