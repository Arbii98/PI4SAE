package aces.esprit.service;

import aces.esprit.entity.Message;

public interface MessageService {

	void addChat(Message message, int idUser);

	void updateChat(Message message, int idUser);

	void deleteChat(int idMsg);

}
