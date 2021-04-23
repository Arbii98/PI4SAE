package aces.esprit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Category;
import aces.esprit.entity.Marque;
import aces.esprit.repository.MarqueRepository;

@Service
public class MarqueService implements IMarqueService {
	

	@Autowired
	MarqueRepository marqueRepository;

	public MarqueService() {
	}

	public MarqueService(MarqueRepository marqueRepository) {
		super();
		this.marqueRepository = marqueRepository;
	}
	

	@Override
	public void addMarque(Marque marque) {
		marqueRepository.save(marque);
	}

	@Override
	public List<Marque> getAllMarques() {
		return (List<Marque>) marqueRepository.findAll();
	}

	@Override
	public Marque getMarqueById(int id) {
		return marqueRepository.findById(id).get();
	}

	@Override
	public void updateMarque(Marque mar, int idMar) {
		if (marqueRepository.findById(idMar).orElse(null) != null)
			mar.setIdMarque(idMar);
		marqueRepository.save(mar);
	}

	@Override
	public void deleteMarqueById(int id) {
		marqueRepository.deleteById(id);
	}

	@Override
	public void deleteAllMarques() {
		marqueRepository.deleteAll();
	}
	
	@Override
	public int getNbrMarques() {
		return marqueRepository.getNbMarques();
	}

	
	
	
}
