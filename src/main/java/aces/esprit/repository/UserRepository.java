package aces.esprit.repository;

import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
