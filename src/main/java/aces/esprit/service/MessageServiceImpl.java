package aces.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Message;
import aces.esprit.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageRepository messageRepository;

	@Override
	public void addChat(Message message, int idUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateChat(Message message, int idUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteChat(int idMsg) {
		// TODO Auto-generated method stub

	}

}
