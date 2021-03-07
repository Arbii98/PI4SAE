package aces.esprit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Admin;
import aces.esprit.entity.Publication;
import aces.esprit.repository.PublicationRepository;
import aces.esprit.repository.UserRepository;
import aces.esprit.service.PublicationService;

@Service
public class PublicationServiceImpl implements PublicationService {
	@Autowired
	PublicationRepository publicationRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void addPublication(Publication pub, int idUser) {
		Admin admin = (Admin) userRepository.findById(idUser).orElse(null);
		if (admin != null) {
			pub.setOwner(admin);
			publicationRepository.save(pub);
		}
	}

	@Override
	public void updatePublication(Publication pub, int idPub) {
		// TODO Auto-generated method stub
		if (publicationRepository.findById(idPub).orElse(null) != null) {
			pub.setIdPub(idPub);
			publicationRepository.save(pub);
		}
	}

	@Override
	public void deletePublication(int idPub) {
		// TODO Auto-generated method stub
		publicationRepository.deleteById(idPub);

	}

	@Override
	public Publication getByIdpub(int idPub) {
		// TODO Auto-generated method stub
		return publicationRepository.findById(idPub).orElse(null);
	}

	@Override
	public List<Publication> getallpub() {
		// TODO Auto-generated method stub
		return (List<Publication>) publicationRepository.findAll();
	}

}
