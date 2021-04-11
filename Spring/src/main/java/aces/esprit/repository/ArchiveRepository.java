package aces.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aces.esprit.entity.Archive;
import aces.esprit.entity.Category;

@Repository
public interface ArchiveRepository extends CrudRepository<Archive, Integer>{

	

}
