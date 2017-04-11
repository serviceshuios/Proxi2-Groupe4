package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	public void AjouterConseiller(Gerant g, Conseiller co) {
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
	public void SupprimerConseiller(Conseiller c, Gerant g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AfficherConseiller(Conseiller c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ModifierConseiller(Conseiller c, Adresse a, String telephone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AjouterClient(Conseiller co, Client c) throws LeConseillerADeja10Clients {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ModifierClient(Client c, Adresse a, String telephone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SupprimerClient(Client c, Conseiller co) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AfficherClient(Client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ActivationCarteVisa(Compte c, CarteBancaire cv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DesactivationCarteVisa(Compte c, CarteBancaire cv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte creationCompte(Compte c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AjouterCompteClient(Client c, Compte co)
			throws CompteEpargneExistantException, CompteCourantExistantException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SupprimerCompteClient(Compte compte, Client c)
			throws AbsenceDeCompteEpargneException, AbsenceDeCompteCourantException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AfficherCompteClient(Compte compte) {
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
	public void EffectuerVirement(int montant, Compte c1, Compte c2)
			throws MontantNegatifException, MontantSuperieurAuSoldeException, DecouvertNonAutorise {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double EffectuerSimulationCredit(double montant, int taux, int duree) throws MontantNegatifException {
		// TODO Auto-generated method stub
		return 0;
	}

}
