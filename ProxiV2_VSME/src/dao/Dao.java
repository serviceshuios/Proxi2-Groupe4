package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class Dao implements IDaoConseiller, IDaoGerant {

	@Override
	public String effectuerAudit(Agence agence) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterConseiller(Gerant g, Conseiller co) {
		try {
			Connection conn= DaoConnection.getConnection();
			//3.creer la requete
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Conseiller (nom, prenom, telephone) VALUES ('" + co.getNom() + "','"+ co.getPrenom()+ "','"+ co.getTelephone()+ "')");
			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO Gerant (nom, prenom, telephone) VALUES ('" + co.getNom() + "','"+ co.getPrenom()+ "','"+ co.getTelephone()+ "')");
			//4.executer la requete
			ps.executeUpdate();
			ps2.executeUpdate();
			//ps.setString(1,c.getNom());
			//ps.setString(2,c.getPrenom());
			
			
			//5.afficher le resultat : pas de resultat attendu
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//6.fermer la connection 
			DaoConnection.closeConnection();
			
		}

	}
		
	

	@Override
	public void supprimerConseiller(Conseiller c, Gerant g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficherConseiller(Conseiller c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierConseiller(Conseiller c, Adresse a, String telephone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterClient(Conseiller co, Client c) throws LeConseillerADeja10Clients {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierClient(Client c, String nom, String prenom, Adresse a, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerClient(Client c, Conseiller co) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Client> listerClient(Conseiller co) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activationCarteVisa(Compte c, CarteBancaire cv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desactivationCarteVisa(Compte c, CarteBancaire cv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte creationCompte(Compte c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterCompteClient(Client c, Compte co)
			throws CompteEpargneExistantException, CompteCourantExistantException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerCompteClient(Compte compte, Client c)
			throws AbsenceDeCompteEpargneException, AbsenceDeCompteCourantException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Compte> listerCompteClient (Client c) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client creerClient(Client c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Placement creerPlacement(String typePlacement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerPlacement(Placement placement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effectuerVirement(int montant, Compte c1, Compte c2)
			throws MontantNegatifException, MontantSuperieurAuSoldeException, DecouvertNonAutorise {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double effectuerSimulationCredit(double montant, int taux, int duree) throws MontantNegatifException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Collection<Client> listerClient(String motcle){
		
		return null;
	}

	@Override
	public boolean authentification(String login, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

}
