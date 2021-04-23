package aces.esprit.service;

import java.util.List;
import java.util.Map;
import aces.esprit.entity.Publication;
import aces.esprit.entity.RatingPub;

public interface PublicationService {

	Publication addPublication(Publication pub, int idUser);

	Publication updatePublication(Publication pub, int idPub);

	void deletePublication(int idPub);

	Publication getByIdpub(int idPub);

	Publication getByTitle(String title);

	List<Publication> getallpubByTopComment();
	
	Map<String, Object> getPub(int page, int size);

	List<Publication> getAllPub();

	List<Publication> getAllPublishByTopRating();

	RatingPub affectRatForPub(RatingPub ratp, int idUser, int idPub);

	List<RatingPub> getRatingPub();

	void archAutoPub();

	Map<String, Float> IAScanner(int idPub);

	List<Publication> getAllPubByUser(int idUser);
	
	Map<String, Integer>  nbrLike (int idPub);


}
