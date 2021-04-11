package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.User;
import aces.esprit.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(value="*")
public class UserController {

	@Autowired
	UserService userservice;
	@GetMapping(value = "/getalluser")
	public List<User> getAllUser() {
		return userservice.listUser();
	}
}
