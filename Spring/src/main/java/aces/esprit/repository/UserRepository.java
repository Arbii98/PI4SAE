package aces.esprit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	
}
