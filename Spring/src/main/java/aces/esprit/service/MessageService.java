package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Message;

public interface MessageService {

	void addChat(String content, int idUser, int idReceiver);

	List<Message> getMessage(int idSender, int idReceiver);

	boolean deleteChat(int idMsg);

}
