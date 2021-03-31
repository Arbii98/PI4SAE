package aces.esprit.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.ReponseRec;

@Repository
public interface ReponseRepository extends CrudRepository<ReponseRec,Integer> {

}
