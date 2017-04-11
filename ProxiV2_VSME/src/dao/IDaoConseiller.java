package dao;

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

public interface IDaoConseiller {

	void AjouterClient(Conseiller co, Client c) throws LeConseillerADeja10Clients;
	void ModifierClient(Client c, Adresse a, String telephone);
	void SupprimerClient(Client c, Conseiller co);
	public void AfficherClient(Client c);
	
	public void ActivationCarteVisa(Compte c, CarteBancaire cv);
	public void DesactivationCarteVisa(Compte c, CarteBancaire cv);
	
	
	public Compte creationCompte(Compte c);
	public void AjouterCompteClient (Client c, Compte co) throws CompteEpargneExistantException, CompteCourantExistantException;
	public void SupprimerCompteClient (Compte compte, Client c) throws AbsenceDeCompteEpargneException, AbsenceDeCompteCourantException;
	public void AfficherCompteClient (Compte compte);
	public Client creerClient(Client c);
	
	public Placement creerPlacement(String typePlacement);
	public void supprimerPlacement(Placement placement);
	void EffectuerVirement(int montant,Compte c1, Compte c2 )throws MontantNegatifException, MontantSuperieurAuSoldeException, DecouvertNonAutorise;
	double EffectuerSimulationCredit(double montant, int taux, int duree) throws MontantNegatifException;
	
	
}
