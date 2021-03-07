package aces.esprit.service;

import java.util.List;

import aces.esprit.entity.Publication;

public interface PublicationService {

	
	void addPublication(Publication pub ,int idUser);
	void updatePublication(Publication pub, int idPub);
	void deletePublication(int idPub);
	Publication getByIdpub(int idPub);
	List<Publication> getallpub();

}
