package dao;

import java.sql.ResultSet;
import java.util.Collection;

import metier.Adresse;
import metier.Agence;
import metier.CarteBancaire;
import metier.Client;
import metier.Compte;
import metier.Conseiller;
import metier.Gerant;
import metier.Placement;
import service.exception.AbsenceDeCompteCourantException;
import service.exception.AbsenceDeCompteEpargneException;
import service.exception.CompteCourantExistantException;
import service.exception.CompteEpargneExistantException;
import service.exception.DecouvertNonAutorise;
import service.exception.LeConseillerADeja10Clients;
import service.exception.MontantNegatifException;
import service.exception.MontantSuperieurAuSoldeException;

public interface IDao {

	public boolean authentificationConseiller(String login, String pwd);
	
	public int compterNombreClient(Conseiller co);
	
	public void ajouterClient(Conseiller co, Client c) throws LeConseillerADeja10Clients;
	public void modifierClient(Client c, String nom, String prenom, Adresse a, String email);
	public void supprimerClient(Client c, Conseiller co);
	public Collection<Client> listerClient(Conseiller co);
	
	public void activationCarteVisa(Compte c, CarteBancaire cv);
	public void desactivationCarteVisa(Compte c, CarteBancaire cv);
	
	
	public Compte creationCompte(Compte c);
	public void ajouterCompteClient (Client c, Compte co) throws CompteEpargneExistantException, CompteCourantExistantException;
	public void supprimerCompteClient (Compte compte, Client c) throws AbsenceDeCompteEpargneException, AbsenceDeCompteCourantException;
	public  Collection<Compte> listerCompteClient (Client c);
	public Collection<Client> listerClient(String motcle);
	public Client creerClient(Client c);
	
	public Placement creerPlacement(String typePlacement);
	public void supprimerPlacement(Placement placement);
	void effectuerVirement(int montant,Compte c1, Compte c2 )throws MontantNegatifException, MontantSuperieurAuSoldeException, DecouvertNonAutorise;
	double effectuerSimulationCredit(double montant, int taux, int duree) throws MontantNegatifException;
	
	
	public String effectuerAudit(Agence agence);
	public void ajouterConseiller(Gerant g, Conseiller co);
	public void supprimerConseiller(Conseiller c, Gerant g);
	public void afficherConseiller(Conseiller c);
	void modifierConseiller(Conseiller c, Adresse a, String telephone);
	
	
}