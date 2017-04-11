package service;

import java.util.Collection;

import metier.Adresse;
import metier.CarteBancaire;
import metier.Client;
import metier.Compte;
import metier.Conseiller;
import metier.Placement;
import service.exception.AbsenceDeCompteCourantException;
import service.exception.AbsenceDeCompteEpargneException;
import service.exception.CompteCourantExistantException;
import service.exception.CompteEpargneExistantException;
import service.exception.DecouvertNonAutorise;
import service.exception.LeConseillerADeja10Clients;
import service.exception.MontantNegatifException;
import service.exception.MontantSuperieurAuSoldeException;

/**
 * 
 * Interface ConseillerClient faisant appelle aux méthodes d'Ajout/Modification/Suppression/Affichage d'un client par un Conseiller
 * @author ME VS
 * 
 *
 *
 */

public interface IConseiller {
	
	
	public void ajouterClient(Conseiller co, Client c) throws LeConseillerADeja10Clients;
	public void modifierClient(Client c, String nom, String prenom, Adresse a, String email);
	public void supprimerClient(Client c, Conseiller co);
	public Collection<Client> listerClient(Conseiller co);
	public Collection<Client> listerClient(String motcle);
	
	public void activationCarteVisa(Compte c, CarteBancaire cv);
	public void desactivationCarteVisa(Compte c, CarteBancaire cv);
	
	
	
	public void ajouterCompteClient (Client c, Compte co) throws CompteEpargneExistantException, CompteCourantExistantException;
	public void supprimerCompteClient (Compte compte, Client c) throws AbsenceDeCompteEpargneException, AbsenceDeCompteCourantException;
	public Collection<Compte> listerCompteClient (Client c);
	
	
	public Placement creerPlacement(String typePlacement);
	public void supprimerPlacement(Placement placement);
	
	public void effectuerVirement(int montant,Compte c1, Compte c2 )throws MontantNegatifException, MontantSuperieurAuSoldeException, DecouvertNonAutorise;
	public double effectuerSimulationCredit(double montant, int taux, int duree) throws MontantNegatifException;
	
	
	
	
}
