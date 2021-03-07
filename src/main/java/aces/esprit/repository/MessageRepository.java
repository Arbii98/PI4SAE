package aces.esprit.repository;

import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.Message;




public interface MessageRepository extends CrudRepository<Message, Integer> {

}
