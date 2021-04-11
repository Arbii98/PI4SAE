package aces.esprit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Message;
import aces.esprit.service.MessageService;

@RestController
@RequestMapping("msgs")
public class MessageController {
	
	@Autowired
	MessageService messageservice;
	
	@PostMapping("{idUser}/{idReceiver}")
	public void addChat(@Valid @RequestBody String content ,@PathVariable int idUser,@PathVariable int idReceiver) {
		messageservice.addChat(content, idUser, idReceiver) ;
	

	}
	@GetMapping("{idSender}/{idReceiver}")
	public 	List<Message> getMessage(@PathVariable int idSender, @PathVariable int idReceiver) {
		return messageservice.getMessage(idSender, idReceiver);
	}

	@DeleteMapping("{idMsg}")
	public ResponseEntity<Void> deleteChat(@PathVariable int idMsg) {

		boolean m = messageservice.deleteChat(idMsg);
		if (m)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
		}

