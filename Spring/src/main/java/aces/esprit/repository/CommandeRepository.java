package aces.esprit.repository;

import org.springframework.data.repository.CrudRepository;

import aces.esprit.entity.Commande;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {

}
