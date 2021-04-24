package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Message;
import aces.esprit.entity.MessageDTO2;

public interface MessageService {

	void addChat(String content, int idUser, int idReceiver);

	List<Message> getMessage(int idSender, int idReceiver);

	boolean deleteChat(int idMsg);

	
	List<MessageDTO2> getLastMessage(int id);
}
