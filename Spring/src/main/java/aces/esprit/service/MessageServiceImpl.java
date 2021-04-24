package aces.esprit.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Message;
import aces.esprit.entity.MessageDTO2;
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
	public void addChat(String content, int idUser, int idReceiver) {

		User userSender = this.userRepository.findById(idUser).orElse(null);
		User userReceiver = this.userRepository.findById(idReceiver).orElse(null);

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
		if (!(new Date(msg.getDate().getYear(), msg.getDate().getMonth(), msg.getDate().getDay(),
				msg.getDate().getDate(), msg.getDate().getDate())
						.before(new Date(now.getYear(), now.getMonth(), now.getDay() - 1)))) {
			messageRepository.deleteById(idMsg);
		}
		return false;

	}

	@Override
	public List<Message> getMessage(int idSender, int idReceiver) {

		return messageRepository.findBySenderAndReceiver(idSender, idReceiver);
	}

	@Override
	public List<MessageDTO2> getLastMessage(int id) {
		List<MessageDTO2> lastMessage = new ArrayList<>();
		Message m;
		for (User user : userRepository.findAll()) {
			if (messageRepository.findBySenderAndReceiver(id, user.getId()).size() > 0) {
				m = messageRepository.findBySenderAndReceiverdesc(id, user.getId()).get(0);
				lastMessage.add(
						new MessageDTO2(user.getId(), user.getName(), user.getImage(), m.getContent(), m.getDate()));
			}

		}
		Collections.sort(lastMessage, (m1, m2) -> {
			return m1.getDate().compareTo(m2.getDate());
		});

		Collections.reverse(lastMessage);
		return lastMessage;
	}

}
