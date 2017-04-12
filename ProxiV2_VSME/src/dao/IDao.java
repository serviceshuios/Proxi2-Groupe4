package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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

	
	public double recuperationSolde(Compte c1) throws SQLException;
	public int recuperationidAdresse(Client c) throws SQLException;
	
	public int authentificationConseiller(String login, String pwd);
	public int compterNombreClient(int idcon);
	
	public void ajouterClient(int idcon, Client c) throws LeConseillerADeja10Clients;
	public void modifierClient(Client c, String nom, String prenom, Adresse a, String email);
	public void supprimerClient(Client c, int idcon);
	public Collection<Client> listerClient(int idcon);
	
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
	public void ajouterConseiller(int idge, Conseiller co);
	public void supprimerConseiller(Conseiller c, int idge);
	public void listerConseiller(int idge);
	public void modifierConseiller(Conseiller c, Adresse a, String telephone);
	public int recuperationidAdresse(Adresse a) throws SQLException;
	public int recuperationidClient(int idPersonne) throws SQLException;
	public int recuperationidPersonne(Client c) throws SQLException;
	public String recuperationTypeCompte(Compte c) throws SQLException;
	public Collection<Client> recuperationClient(int idCli) throws SQLException;
	int recuperationidClientParticulier(int idCli) throws SQLException;
	int recuperationidClientEntreprise(int idCli) throws SQLException;
	
	
}
