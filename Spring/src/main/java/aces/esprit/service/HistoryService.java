package aces.esprit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.History;
import aces.esprit.entity.Users;
import aces.esprit.repository.HistoryRepository;

@Service
public class HistoryService implements IHistoryService {
	
	@Autowired
	HistoryRepository hp;

	@Override
	public void addHistory(History history) {
		Date date = new Date(System.currentTimeMillis());
		history.setDate(date);
		hp.save(history);
		
	}

	@Override
	public List<History> getHistories() {
		return (List<History>) hp.findAll();
	}

	@Override
	public List<History> getByUsers(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

}
