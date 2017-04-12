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
import metier.ClientEntreprise;
import metier.ClientParticulier;
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

public class Dao implements IDao {
	
	/**
	 * Methode d'authentification du conseiller (v�rification du mot de passe en fonction du login)
	 * 
	 */
	//la m�thode renvoie l'id du conseiller si le mot de passe est associ� au login  et renvoie 0 si il n'est pas associ�
	@Override
	public int authentificationConseiller(String login, String pwd) {
		
		int id = 0; //initialisation de l'idConseiller � 0
		try {
			
			//Selection du mot de passe et l'idConseiller associ�s au login
		Connection conn= DaoConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT pwd, idConseiller FROM connectionconseiller WHERE login = ?");
		
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
		
		//si le login existe, alors resultsat contient un mot de passe
		if (rs.next())
		{
			
			String password = rs.getString("pwd");
			//test si le mot de passe envoy� correspond au mot de passe en base de donn�es 
			if(password.equals(pwd))
			{
				//si il correspond, on renvoie l'idConseiller qui servira dans d'autres methodes
			id=rs.getInt("idConseiller");
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoConnection.closeConnection();
		//cas o� le mot de passe ne correspond pas � celuyi en base de donn�es : la m�thode renvoie 0
		return id;
		
	}
	
	
	

	
	/**
	 * Methode pour lister les clients d'un conseill� 
	 */
	@Override
	public Collection<Client> listerClient(int idcon) {
		
		//instantiation d'une collecton de clients
		Collection<Client> cl = new ArrayList<Client>();
		try {
			Connection conn= DaoConnection.getConnection();
			//requete SQL pour r�cup�rer idclient, nom, prenom, telephone, email, adresse, codepostale, ville, type de client
			PreparedStatement ps = conn.prepareStatement("SELECT client.idClient, nom, prenom, telephone, email, adresse, codepostale, ville, typeClient FROM client, personne, adresse, conseiller WHERE conseiller.idConseiller=client.idConseiller AND adresse.idAdresse=personne.idAdresse AND personne.idClient=client.idClient and conseiller.idConseiller=? ");
			ps.setInt(1, idcon);
			ResultSet rs = ps.executeQuery();
			
			//methode de remplissage de la collection clients avec les valeurs selectionn�es
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
		//retourne la collection de clients obtenue
		return cl;
		
		
	}
	
	
	/**
	 * Methode pour r�cup�rer l'idAdresse d'un client grace � son idClient
	 */
	@Override
	public int recuperationidAdresse(Client c) throws SQLException{
		
		//requete sql pour r�cuperer l'adresse d'une client � partir de l'idClient
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
			//retourne l'idAdresse
		return id;
	}
	
	/**
	 * Methode pour r�cup�rer l'idAdresse � partir d'une adresse dans la base de donn�es
	 */
	@Override
	public int recuperationidAdresse(Adresse a) throws SQLException{
		
		//requete sql pour r�cuperer l'adresse d'une client � partir d'une adresse complete
		Connection conn= DaoConnection.getConnection();
		int id=0;
			String selection = "SELECT idadresse FROM adresse WHERE adresse = ? AND codepostale = ? AND  ville = ?";
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
			//retourne l'idAdresse
		return id;
	}
	
	/**
	 * Methode de modification d'un client en Base de donn�es
	 */
	@Override
	public void modifierClient(Client c, String nom, String prenom, Adresse a, String email) {
		try {
			IDao idao = new Dao();
			
			//Requete sql pour changer le nom, le prenom et l'email du client dans la table personne a partir de l'idclient
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
	public int recuperationidClient(int idPersonne) throws SQLException{
		Connection conn= DaoConnection.getConnection();
		int id=0;
			String selection = "SELECT idClient FROM client WHERE id = ?";
			PreparedStatement psselection = conn.prepareStatement(selection);
			psselection.setInt(1, idPersonne);
			ResultSet rs1 = psselection.executeQuery();
			if(rs1.next())
		{
				id = rs1.getInt("idClient");
				return id;
		}
		return id;
	}
	
	
	@Override
	public int recuperationidPersonne(Client c)throws SQLException{
		Connection conn= DaoConnection.getConnection();
		int id=0;
			String selection = "SELECT id FROM personne WHERE nom = ? AND prenom = ? AND  email = ?";
			PreparedStatement psselection = conn.prepareStatement(selection);
			psselection.setString(1, c.getNom());
			psselection.setString(2, c.getPrenom());
			psselection.setString(3, c.getEmail());
			ResultSet rs1 = psselection.executeQuery();
			if(rs1.next())
		{
				id = rs1.getInt("id");
				return id;
		}
		return id;
	}
	
	@Override
	public void ajouterClient(int idcon, Client c) throws LeConseillerADeja10Clients {
		try {
			Connection conn= DaoConnection.getConnection();
			String s2= "INSERT INTO personne(nom, prenom, telephone, email) VALUES (?,?,?,?)";
			PreparedStatement ps2 = conn.prepareStatement(s2);
			ps2.setString(1, c.getNom());
			ps2.setString(2, c.getPrenom());
			ps2.setString(3, c.getTelephone());
			ps2.setString(4, c.getEmail());
			ps2.executeUpdate();
			
			IDao idao = new Dao();
			//recuperation idpersonne
			int idPersonne = idao.recuperationidPersonne(c);
			System.out.println(idPersonne);
			
			String s= "INSERT INTO client(typeClient, idConseiller,id) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setString(1, c.getTypeClient());
			ps.setInt(2, idcon);
			ps.setInt(3, idPersonne);
			ps.executeUpdate();
			
			
			
			
			String s3 = "INSERT INTO adresse(adresse, codePostale, ville) VALUES (?,?,?)";
			PreparedStatement ps3 = conn.prepareStatement(s3);
			Adresse a1 = new Adresse();
			a1 = c.getSonAdresse();
			ps3.setString(1, a1.getAdresse());
			ps3.setInt(2, a1.getCodePostale());
			ps3.setString(3, a1.getVille());
			ps3.executeUpdate();
			
			
			
			int idCli = idao.recuperationidClient(idPersonne);
			System.out.println(idCli);
			int idAdresse = idao.recuperationidAdresse(a1);
			System.out.println(idAdresse);
			
			
			String s4= "UPDATE personne SET idClient =?, idAdresse = ? WHERE id = ? ";
			PreparedStatement ps4= conn.prepareStatement(s4);
			ps4.setInt(1, idCli);
			ps4.setInt(2, idAdresse);
			ps4.setInt(3, idPersonne);
			ps4.executeUpdate();
			
			
			if(c instanceof ClientParticulier)
			{
				String s5= "INSERT INTO clientparticulier(idClient) VALUES (?)";
				PreparedStatement ps5 = conn.prepareStatement(s5);
				ps5.setInt(1, idCli);
				ps5.executeUpdate();
				
			}
			
			if(c instanceof ClientEntreprise)
			{
				String s6= "INSERT INTO cliententreprise(idClient) VALUES (?)";
				PreparedStatement ps6 = conn.prepareStatement(s6);
				ps6.setInt(1, idCli);
				ps6.executeUpdate();
			}
			
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
