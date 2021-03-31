package aces.esprit.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Archive;
import aces.esprit.entity.Category;
import aces.esprit.repository.ArchiveRepository;
import aces.esprit.repository.CategoryRepository;

@Service
public class ArchiveService {
	
	@Autowired
	ArchiveRepository archiveRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public ArchiveService() {
	}

	public ArchiveService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	
	public void addArchive(int idCat) {
		
		Category cat = categoryRepository.findById(idCat).get();
		String nomCat = cat.getNameCategory();
		
		Archive arch = new Archive();
		//arch.setIdArchive(idCat);
		arch.setNameArchive(nomCat);
		
		archiveRepository.save(arch);
	}


	
	
	
}
