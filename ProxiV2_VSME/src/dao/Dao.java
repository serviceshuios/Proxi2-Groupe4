package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class Dao implements IDao {
	
	/**
	 * Methode d'authentification du conseiller (vérification du mot de passe en fonction du login)
	 * 
	 */
	//la méthode renvoie l'id du conseiller si le mot de passe est associé au login  et renvoie 0 si il n'est pas associé
	@Override
	public int authentificationConseiller(String login, String pwd) {
		
		int id = 0;
		try {
		Connection conn= DaoConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT pwd, idConseiller FROM connectionconseiller WHERE login = ?");
		
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
		
		
		if (rs.next())
		{
			
			String password = rs.getString("pwd");
			if(password.equals(pwd))
			{
			id=rs.getInt("idConseiller");
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoConnection.closeConnection();
		return id;
		
	}
	
	
	

	
	/**
	 * Methode pour lister les clients d'un conseillé 
	 */
	@Override
	public Collection<Client> listerClient(int idcon) {
		
		Collection<Client> cl = new ArrayList<Client>();
		try {
			Connection conn= DaoConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT client.idClient, nom, prenom, telephone, email, adresse, codepostale, ville, typeClient FROM client, personne, adresse, conseiller WHERE conseiller.idConseiller=client.idConseiller AND adresse.idAdresse=personne.idAdresse AND personne.idClient=client.idClient and conseiller.idConseiller=? ");
			ps.setInt(1, idcon);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
					{
						Adresse a1 = new Adresse(rs.getString("adresse"), rs.getInt("codePostale"), rs.getString("ville"));
						Client c1= new Client ();
						c1.setNom(rs.getString("nom"));
						c1.setPrenom(rs.getString("prenom"));
						c1.setTelephone(rs.getString("telephone"));
						c1.setSonAdresse(a1);
						c1.setTypeClient(rs.getString("typeClient"));
						c1.setEmail(rs.getString("email"));
						cl.add(c1);
					}
			
			} catch (Exception e) 
				{
					e.printStackTrace();
				}
		DaoConnection.closeConnection();
		return cl;
		
		
	}
	
	
	/**
	 * Methode pour récupérer l'idAdresse d'un client grace à son idClient
	 */
	@Override
	public int recuperationidAdresse(Client c) throws SQLException{
		Connection conn= DaoConnection.getConnection();
		int id=0;
			String selection = "SELECT idadresse FROM personne WHERE idClient = ?";
			PreparedStatement psselection = conn.prepareStatement(selection);
			psselection.setInt(1, c.getIdClient());
			ResultSet rs1 = psselection.executeQuery();
			if(rs1.next())
		{
				id = rs1.getInt("idadresse");
				return id;
		}
		return id;
	}
	
	
	@Override
	public int recuperationidAdresse(Adresse a) throws SQLException{
		Connection conn= DaoConnection.getConnection();
		int id=0;
			String selection = "SELECT idadresse FROM adresse WHERE adresse = ? AND code postale = ? AND  ville = ?";
			PreparedStatement psselection = conn.prepareStatement(selection);
			psselection.setString(1, a.getAdresse());
			psselection.setInt(2, a.getCodePostale());
			psselection.setString(3, a.getVille());
			ResultSet rs1 = psselection.executeQuery();
			if(rs1.next())
		{
				id = rs1.getInt("idadresse");
				return id;
		}
		return id;
	}
	
	/**
	 * Methode de modification d'un client en Base de données
	 */
	@Override
	public void modifierClient(Client c, String nom, String prenom, Adresse a, String email) {
		try {
			IDao idao = new Dao();
			
			Connection conn= DaoConnection.getConnection();
			String s= "UPDATE personne SET nom = ?, prenom = ? , email = ?  WHERE personne.idClient = ? ";
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, email);
			ps.setInt(4, c.getIdClient());
			ps.executeUpdate();
			
			int id = idao.recuperationidAdresse(c);
			
			String s2= "UPDATE adresse SET adresse = ? ,codePostale = ? , ville = ?  WHERE idAdresse = ?";
			PreparedStatement ps2 = conn.prepareStatement(s2);
			ps2.setString(1, a.getAdresse());
			ps2.setInt(2, a.getCodePostale());
			ps2.setString(3, a.getVille());
			ps2.setInt(4, id);
			ps2.executeUpdate();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoConnection.closeConnection();
		
	}
	
	/**
	 * Methode pour lister les comptes d'un client
	 */
	@Override
	public Collection<Compte> listerCompteClient (Client c) {
		
		Collection<Compte> co1 = new ArrayList<Compte>();
		try {
			Connection conn= DaoConnection.getConnection();
			String s ="SELECT idCompte, numeroCompte, solde, dateOuverture, typeCompte FROM compte WHERE idClient = ? ";
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setInt(1, c.getIdClient());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
					{
						Compte c1= new Compte ();
						c1.setIdCompte(rs.getInt("idCompte"));
						c1.setNumeroCompte(rs.getInt("numeroCompte"));
						c1.setSolde(rs.getDouble("solde"));
						c1.setDateOuverture(rs.getString("dateOuverture"));
						c1.setTypeCompte(rs.getString("typeCompte"));
						co1.add(c1);
					}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoConnection.closeConnection();

		return co1;
	}
	
	@Override
	public Collection<Client> listerClient(String motcle){
		
		Collection<Client> cl = new ArrayList<Client>();
		try {
			Connection conn= DaoConnection.getConnection();
			String s = "SELECT client.idClient, nom, prenom, telephone, email, adresse, codepostale, ville, typeClient FROM client, personne, adresse WHERE adresse.idAdresse=personne.idAdresse AND personne.idClient=client.idClient AND UPPER(personne.nom) LIKE UPPER('%" + motcle+ "%') ";
			PreparedStatement ps = conn.prepareStatement(s);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
					{
						Adresse a1 = new Adresse(rs.getString("adresse"), rs.getInt("codePostale"), rs.getString("ville"));
						Client c1= new Client ();
						c1.setNom(rs.getString("nom"));
						c1.setPrenom(rs.getString("prenom"));
						c1.setTelephone(rs.getString("telephone"));
						c1.setSonAdresse(a1);
						c1.setTypeClient(rs.getString("typeClient"));
						c1.setEmail(rs.getString("email"));
						cl.add(c1);
					}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoConnection.closeConnection();

		return cl;
	}
	
	@Override
	public double recuperationSolde(Compte c1) throws SQLException{
		Connection conn= DaoConnection.getConnection();
		double solde1=0;
			String selection = "SELECT solde FROM compte WHERE idCompte = ?";
			PreparedStatement psselection = conn.prepareStatement(selection);
			psselection.setInt(1, c1.getIdCompte());
			ResultSet rs1 = psselection.executeQuery();
			if(rs1.next())
		{
				solde1 = rs1.getDouble("solde");
				return solde1;
		}
		return solde1;
	}
	
	
	@Override
	public void effectuerVirement(int montant, Compte c1, Compte c2)
			throws MontantNegatifException, MontantSuperieurAuSoldeException, DecouvertNonAutorise {
				try {
					Connection conn= DaoConnection.getConnection();
					IDao idao = new Dao();
					
					double solde1= idao.recuperationSolde(c1);
					double solde2=idao.recuperationSolde(c2);
					
					String s= "UPDATE compte SET solde = ? WHERE idCompte = ? ";
					PreparedStatement ps = conn.prepareStatement(s);
					ps.setDouble(1, (solde1-montant));
					ps.setLong(2, c1.getIdCompte());
					ps.executeUpdate();
					
					String s1= "UPDATE compte SET solde = ? WHERE idCompte = ? ";
					PreparedStatement ps1 = conn.prepareStatement(s1);
					ps1.setDouble(1, (solde2+montant));
					ps1.setLong(2, c2.getIdCompte());
					ps1.executeUpdate();
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DaoConnection.closeConnection();
				
	}
	
	@Override
	public int compterNombreClient(int idcon)
	{
		int i=0;
		try {
			Connection conn= DaoConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT (idClient)nombreClient FROM client WHERE idConseiller = ?");
			ps.setInt(1, idcon);
			ResultSet rs = ps.executeQuery();
			
			i = rs.getInt("nombreClient");
			return i;
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoConnection.closeConnection();
		return i;
		
	}
	
	
	@Override
	public int recuperationidClient(Client c) throws SQLException{
		Connection conn= DaoConnection.getConnection();
		int id=0;
			String selection = "SELECT idclient FROM personne WHERE nom = ? AND prenom = ? AND  email = ?";
			PreparedStatement psselection = conn.prepareStatement(selection);
			psselection.setString(1, c.getNom());
			psselection.setString(2, c.getPrenom());
			psselection.setString(3, c.getEmail());
			ResultSet rs1 = psselection.executeQuery();
			if(rs1.next())
		{
				id = rs1.getInt("idadresse");
				return id;
		}
		return id;
	}
	
	
	
	@Override
	public void ajouterClient(int idcon, Client c) throws LeConseillerADeja10Clients {
		try {
			Connection conn= DaoConnection.getConnection();
			String s= "INSERT INTO client(typeClient,idConseiller) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setString(1, c.getTypeClient());
			ps.setInt(2, idcon);
			String s3 = "INSERT INTO adresse(adresse, codePostale, ville) VALUES (?,?,?)";
			PreparedStatement ps3 = conn.prepareStatement(s3);
			Adresse a1 = new Adresse();
			a1 = c.getSonAdresse();
			ps3.setString(1, a1.getAdresse());
			ps3.setInt(2, a1.getCodePostale());
			ps3.setString(3, a1.getVille());
			String s2= "INSERT INTO personne(nom, prenom, telephone, email, idClient, idAdresse) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps2 = conn.prepareStatement(s2);
			ps2.setString(1, c.getNom());
			ps2.setString(2, c.getPrenom());
			ps2.setString(3, c.getTelephone());
			ps2.setString(4, c.getEmail());
			
			IDao idao = new Dao();
			
			int idClient = idao.recuperationidClient(c);
			ps2.setInt(5, idClient);
			
			int idAdresse = idao.recuperationidAdresse(c.getSonAdresse());
			ps2.setInt(6, idAdresse);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoConnection.closeConnection();
			
		
	}
	
	
	
	
	
	@Override
	public String effectuerAudit(Agence agence) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterConseiller(int idge, Conseiller co) {
		

	}
		
	

	@Override
	public void supprimerConseiller(Conseiller c, int idge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listerConseiller(int idge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierConseiller(Conseiller c, Adresse a, String telephone) {
		// TODO Auto-generated method stub
		
	}

	

	

	@Override
	public void supprimerClient(Client c, int idcon) {
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
	public double effectuerSimulationCredit(double montant, int taux, int duree) throws MontantNegatifException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	

}
