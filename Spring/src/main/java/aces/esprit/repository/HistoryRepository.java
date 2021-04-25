package aces.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.History;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer>{

}
