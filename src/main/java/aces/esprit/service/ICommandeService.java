package aces.esprit.service;

import aces.esprit.entity.Commande;

public interface ICommandeService {
	
	public Commande addCommande(Commande commande,int idClient);

}
