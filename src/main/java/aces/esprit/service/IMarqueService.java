package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Marque;

public interface IMarqueService {

	void addMarque(Marque marque);

	List<Marque> getAllMarques();

	Marque getMarqueById(int id);

	void updateMarque(Marque mar, int idMar);

	void deleteMarqueById(int id);

	void deleteAllMarques();

	int getNbrMarques();

}
