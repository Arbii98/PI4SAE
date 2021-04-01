package aces.esprit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Message;
import aces.esprit.entity.User;
import aces.esprit.repository.MessageRepository;
import aces.esprit.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	UserRepository userRepository; 

	@Override
	public void addChat(String content , int idUser, int idReceiver) {

		User userSender = this.userRepository.findById(idUser).orElse(null);
		User userReceiver = this. userRepository .findById(idReceiver).orElse(null);

		Message message = new Message();
		message.setContent(content);
		message.setDate(new Date());
		message.setSender(userSender);
		message.setReceiver(userReceiver);
		this.messageRepository.save(message);
	}



	@Override
	public boolean deleteChat(int idMsg) {
		Message msg = this.messageRepository.findById(idMsg).orElse(null);
		Date now = new Date();
		if (!(new Date(msg.getDate().getYear(), msg.getDate().getMonth(),msg.getDate().getDay(), msg.getDate().getDate(),msg.getDate().getDate())
				.before(new Date(now.getYear(), now.getMonth() ,now.getDay()- 1)) ))
		{
			messageRepository.deleteById(idMsg);
		}
		return false;
		

	}



	@Override
	public List<Message> getMessage(int idSender, int idReceiver) {
	
		return messageRepository.findBySenderAndReceiver(idSender, idReceiver);
	}

}
