package aces.esprit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.User;
import aces.esprit.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userrepo;
	
	
	public List<User> listUser()
	{
		return (List<User>) userrepo.findAll();
	}
}
