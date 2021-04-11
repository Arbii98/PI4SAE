package aces.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aces.esprit.entity.LikeProduct;
import aces.esprit.entity.LikedProduct;
import aces.esprit.entity.Product;
import aces.esprit.entity.User;
import aces.esprit.repository.LikeRepository;
import aces.esprit.repository.ProductRepository;
import aces.esprit.repository.UserRepository;

@Service
public class LikeService implements ILikeSerive{
	
	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;

	public LikeService() {
	}

	public LikeService(LikeRepository likeRepository, UserRepository userRepository, ProductRepository productRepository) {
		this.likeRepository = likeRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}
	
	
	@Override
	public void like(int idproduit, int iduser) {
		
		User user = userRepository.findById(iduser).get();
		Product p = productRepository.findById(idproduit).get();
		
		LikeProduct lp = new LikeProduct();
		lp.setNameLike(LikedProduct.LIKE);
		lp.setProduct(p);
		lp.setUser(user);
		
		likeRepository.save(lp);
		
	}
	
	
	

}
