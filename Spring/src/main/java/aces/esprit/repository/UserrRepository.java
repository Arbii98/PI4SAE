package aces.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Userr;

@Repository
public interface UserrRepository extends CrudRepository<Userr, Integer>{

}
