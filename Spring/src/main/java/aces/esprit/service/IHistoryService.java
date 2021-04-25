package aces.esprit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import aces.esprit.entity.History;
import aces.esprit.entity.Users;

@Service
public interface IHistoryService {
	void addHistory(History history);
	List<History> getHistories();
	List<History> getByUsers(Users user);
}
