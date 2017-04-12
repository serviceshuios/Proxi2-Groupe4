package service;

import java.util.Collection;

import dao.Dao;
import dao.IDao;
import metier.Adresse;
import metier.Agence;
import metier.CarteBancaire;
import metier.Client;
import metier.ClientEntreprise;
import metier.ClientParticulier;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
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

public class Services implements IConseiller, IGerant {

	IDao idao = new Dao();

	
	public Services() 
		{
			super();
		}
	
	/**
	 * Authentification du conseiller
	 */
	@Override
	public int authentificationConseiller(String login, String pwd) 
		{
			return idao.authentificationConseiller(login,pwd);
		}
	
	
	/**
	 * Lister les clients d'un conseiller
	 */
	@Override
	public Collection<Client> listerClient(int idcon) 
		{
			return idao.listerClient(idcon);
		}
	
	
	/**
	 * Modification du nom, prenom, adresse et email d'un client
	 */
	@Override
	public void modifierClient(Client c, String nom, String prenom, Adresse a, String email) 
		{
			idao.modifierClient(c, nom, prenom,a,email);	
		}
	
	
	/**
	 * Lister les comptes d'un client
	 */
	@Override
	public Collection<Compte> listerCompteClient (Client c) 
		{
			return idao.listerCompteClient (c);
		}


		
	/**
	 * Lister les clients par mot clé (recherche dans le nom)
	 */
	@Override
	public Collection<Client> listerClient(String motcle)
		{
			return idao.listerClient(motcle);	
		}
	
	
	
	/**
	 * Virement d'un montant d'un compte c1(débiteur) à un compte c2(créditeur) 
	 * @throws MontantSuperieurAuSoldeException 
	 * @throws DecouvertNonAutorise 
	 */
	
	@Override
	public void effectuerVirement(int montant,Compte c1, Compte c2) throws MontantNegatifException, MontantSuperieurAuSoldeException, DecouvertNonAutorise {
		
		if (montant<0) //Test si le montant entré est inférieur à 0
		{                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			throw new MontantNegatifException("montant inférieur à zero");
		}
		else 
		{
			if(c1 instanceof CompteEpargne) //Test si le compte est un compte Epargne
			{
				if(montant<c1.getSolde()) // Test si le montant est inferieur au solde du compte
				{
					idao.effectuerVirement(montant, c1, c2);
				}
				else
				{
					throw new MontantSuperieurAuSoldeException("montant supperieur au solde");
				}
			}
			else
			{
				if(c1 instanceof CompteCourant) //Test si le compte est un compte Courant
				{
					if((c1.getSolde()-montant)>-1000) //Test si le solde du compte viré est au dessus du découvert autorisé
					{
						idao.effectuerVirement(montant, c1, c2);
					}
					else
					{
						throw new DecouvertNonAutorise("le decouvert n'autorise pas ce virement");
					}
				}
			
			}
		}
			
	}

	
	@Override
	public void ajouterClient(int idcon, Client c) throws LeConseillerADeja10Clients {
		
		if(idao.compterNombreClient(idcon)<10)
				{ 
		
			//if(c instanceof ClientParticulier || c instanceof ClientEntreprise){ //Test si client entreprise ou particulier
		
				idao.ajouterClient(idcon, c);
				
			//}
			
		} else{
			throw new LeConseillerADeja10Clients("Vous avez déjà 10 clients.");
		}	
	
	}
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Ajout d'un conseiller par un gérant
	 */
	@Override
	public void ajouterConseiller(int idge, Conseiller co) 
		{
		idao.ajouterConseiller(idge, co);
			
			/*Collection<Conseiller> col = g.getConseillers(); // Récupère la liste des conseillers du gérant
			col.add(co); //Ajoute le Conseiller co à la liste col
			g.setConseillers(col); // Associe la nouvelle liste des conseillers au gérant
			co.setGerant(g); // Associe le nouveau conseiller à son gérant
			*/
			
		}

	/**
	 * Modification d'un conseiller par un gérant
	 */
		@Override
		public void modifierConseiller(Conseiller c, Adresse a, String telephone) {
			
			idao.modifierConseiller(c, a, telephone);
			/*c.setSonAdresse(a);
			c.setTelephone(telephone);
			*/
			
		}

	/**
	 * Suppression d'un conseiller par un gérant
	 */
		@Override
		public void supprimerConseiller(Conseiller c, int idge) {
			
			idao.supprimerConseiller(c, idge);
		
			/*
			Collection<Conseiller> col1 = g.getConseillers(); //Récupère la liste des conseillers du gérant
			col1.remove(c); //supprimer le conseiller c
			g.setConseillers(col1); //Associe la nouvelle liste au gérant
			*/
		
			
		}
	/**
	 * Affichage d'un conseiller par un gérant
	 */

		@Override
		public void listerConseiller(int idge) {
			idao.listerConseiller(idge);
		}
	
	
		
		public String effectuerAudit(Agence agence) {
			return idao.effectuerAudit(agence);
			/*String res = "Audit de l'Agence " + agence.getIdAgence();
		Iterator<Conseiller> itCons = agence.getGerant().getConseillers().iterator();
		while (itCons.hasNext()) {
			Iterator<Client> itClient = itCons.next().getClients().iterator();
			while (itClient.hasNext()) {
				Iterator<Compte> itCompte = itClient.next().getComptes().iterator();
				while (itCompte.hasNext()) {
					Compte eval = itCompte.next();
					if (eval.getClient() instanceof ClientParticulier) {
						if (eval.getSolde() < 5000) {
							res += "" + eval.getClient().getConseiller() + eval.getClient() + eval + eval.getSolde();
						}
						if (eval.getClient() instanceof ClientEntreprise) {
							if (eval.getSolde() < 50000) {
								res += "" + eval.getClient().getConseiller() + eval.getClient() + eval
										+ eval.getSolde();
							}

						}
					}
				}
			}
		}
		return res;
*/
	}

	
	/**
	 * Methode de creation d'un Client
	 * 
	 * 
	 * @param comptes
	 *            parametre qui donne la liste de compte du client
	 * @param patrimoine
	 *            parametre qui donne le patrimoine du client
	 * @param credits
	 *            paramettre qui donne les credits du client
	 * @param conseiller
	 *            parametre qui donne le conseiller du client
	 * @param typeClient
	 *            parametre qui premet de choisir le type de client
	 * @return retourne l'Objet Client Creer
	 */

		private Client creerClient(Client c) {

			return idao.creerClient(c);
			/*
			// pour choisir type de client

			if(c.getTypeClient().equals("particulier")) {
				ClientParticulier p = new ClientParticulier();
				return p;
			}
			if(c.getTypeClient().equals("entreprise"))
				{
				ClientEntreprise e = new ClientEntreprise();
				return e;
			}
			
			return null;
			*/
		}
			
		
		
	
	
	/**
	 * Ajout d'un compte Epargne ou un Compte Courant à un client
	 * @throws CompteEpargneExistantException 
	 * @throws CompteCourantExistantException 
	 */
	
	
	private Compte creationCompte(Compte c) {

		return idao.creationCompte(c);
		// pour choisir type de client

		/*
		 * if(c.getTypeCompte().equals("epargne")) {
		 
			CompteEpargne ce = new CompteEpargne();
			return ce;
		}
		if(c.getTypeCompte().equals("courant"))
			{
			CompteCourant cc = new CompteCourant();
			return cc;
		}
		
		return null;
		*/
	}
	
	
		@Override
		public void ajouterCompteClient(Client c, Compte co) throws CompteEpargneExistantException, CompteCourantExistantException {
			
			idao.ajouterCompteClient(c, co);
			
			/*
			IConseiller cs = new Services();
			co=cs.creationCompte(co);
			
			if(co instanceof CompteEpargne){ //Test si le compte à ajouter est un Compte Epargne
				boolean b=true;
				for (Compte compte : c.getComptes())
				{
					if(compte instanceof CompteEpargne)
					{
						b=false;
					}
				}
				if(b=true){ // Test pour savoir si le client a déjà un compte epargne
				
					c.setComptes((Collection<Compte>) co);
					System.out.println("Le compte Epargne a été ajouté.");
				}else{
					throw new CompteEpargneExistantException("Le client a déjà un Compte Epargne.");
				}				
			
			}
			else{
				if(co instanceof CompteCourant){	// Dans le cas d'un ajout de Compte Courant
					boolean b2=true;
					for (Compte compte : c.getComptes())
					{
						if(compte instanceof CompteCourant)
						{
							b2=false;
						}
					}
					if(b2=true){ // Test pour savoir si le client a déjà un compte courant
					
						c.setComptes((Collection<Compte>) co);
						System.out.println("Le compte courant a été ajouté.");
											
					}
					else{
						throw new CompteCourantExistantException("Le client a déjà un Compte Courant.");
					}
				
				}
				
							
			}		
			*/	
		}

	
	
	
	/**
	 * Supprimer un client de la liste d'un conseiller
	 */

		@Override
		public void supprimerClient(Client c, int idcon) {
			idao.supprimerClient(c, idcon);
			/*
			Collection<Client> col = co.getClients(); //Récupération de la liste des clients du conseiller dans la collection col
			col.remove(c);	//Suppression du client de la collection
			co.setClients(col);	//Association de la Collection mise à jour au conseiller
			*/
		}
	
	
	
	
	/**
	 * Suppression d'un compte Epargne ou compte courant
	 * @throws AbsenceDeCompteEpargneException 
	 * @throws AbsenceDeCompteCourantException 
	 */

		@Override
		public void supprimerCompteClient(Compte co, Client c) throws AbsenceDeCompteEpargneException, AbsenceDeCompteCourantException {
			idao.supprimerCompteClient(co, c);
			
			/*
			if(co instanceof CompteEpargne){ //Test si le compte à supprimer est un Compte Epargne
				boolean b=false;
				for (Compte compte : c.getComptes())
				{
					if(compte == co)
					{
						b=true;
					}
				} // Test pour savoir si le client a  bien ce compte
				if(b==true)
				{
				co = null;
				System.out.println("Le compte Epargne a été supprimé.");
				
				}else{
					throw new AbsenceDeCompteEpargneException("Le compte n'appartient pas au Client");
				}				
			
			}
			else{
				if(co instanceof CompteCourant){
					boolean b=false;
					for (Compte compte : c.getComptes())
					{
						if(compte == co)
						{
							b=true;
						}
					}// Dans le cas d'une suppression du Compte Courant
					if(b==true){ // Test pour savoir si le client a déjà un compte courant
						
						
						co=null;
						System.out.println("Le compte courant a été supprimé.");
											
					}
					else{
						throw new AbsenceDeCompteCourantException("Le Client n'a pas ce compte courant.");
					}
				}
										
			}			
			*/
		}

	
			
	
	/**
	 * Realisation d'une simulation de crédit avec un montant, un taux et une durée de remboursement
	 * @throws MontantNegatifException 
	 */
		@Override
		public double effectuerSimulationCredit(double montant, int taux, int duree) throws MontantNegatifException {
			
			return idao.effectuerSimulationCredit(montant, taux, duree);
			/*
			double montantARembourserParMois;
			if (montant<=0) //Test si le montant entré est inférieur à 0
			{
				montantARembourserParMois = 0;
				throw new MontantNegatifException("montant de remboursement negatif");
			}
			else
			{
			double montantARembourser = montant*(1+(taux/100)); //Calcul de la somme totale à remboursé (montant avec les interets)
			montantARembourserParMois = montantARembourser/duree; //Calcul des mensualités à rembourser par le client
			}
			return montantARembourserParMois;
		*/
		
		}

	/**
	 * la methode créée un placement et l'ajoute au patrimoine du client
	 * 
	 * @param patrimoine
	 *            parametre qui donne lepatrimoine du client
	 * @param typePlacement
	 *            parametre qui permet de choisir le type de placement
	 * @return retourne le placement crée
	 */
	public Placement creerPlacement(String typePlacement) {

		return idao.creerPlacement(typePlacement);
		/*
		// calcul de la fortune client
		Collection<Compte> col = patrimoine.getClient().getComptes();
		double fortune = 0;

		Iterator<Compte> it = col.iterator();
		while (it.hasNext())
		{
			fortune = fortune + (it.next().getSolde());
		}
		// condition de fortune pour creer un placement
		if (fortune > 500000) {

			Collection<Placement> pl = new ArrayList<Placement>();
			pl = patrimoine.getPlacements();
			// creer le placement en choisissant la bourse
			if(typePlacement.equals("Paris")) {
			
				Placement p = new Placement(patrimoine);
				pl.add(p);
				patrimoine.setPlacements(pl);
				return p;
			}

			if(typePlacement.equals("Tokyo")) {
				Placement p = new Placement(patrimoine);
				pl.add(p);
				patrimoine.setPlacements(pl);
				return p;
			}
			if(typePlacement.equals("NewYork")) {
				Placement p = new Placement(patrimoine);
				pl.add(p);
				patrimoine.setPlacements(pl);
				return p;
			}
			 

		
		}
		return null;
		*/
	}

	/**
	 * Methode pour supprimer un placement
	 * 
	 * @param placement
	 *            parametre qui donne le placement a supprimmer
	 */
	public void supprimerPlacement(Placement placement) {
		
		idao.supprimerPlacement(placement);
		
		/*

		Collection<Placement> col = placement.getPatrimoine().getPlacements();
		Collection<Placement> col2 = new ArrayList<Placement>();
		Iterator<Placement> it = col.iterator();
		while (it.hasNext())
			if (it.next().getIdPlacement() == placement.getIdPlacement()) {
				// ne fais rien quand on arrive sur le placement a supprimer
			} else {
				col2.add(it.next()); // ajoute les placements que l'on ne
										// supprimme pas dans une Collection
			}

		placement.getPatrimoine().setPlacements(col2); // met a jour la
														// Collection
														 * 
														 */

	}
	
	

		
	/**
	 * Ajout d'une carte à un compte
	 */

		@Override
		public void activationCarteVisa(Compte c, CarteBancaire cv) {
			
			idao.activationCarteVisa(c, cv);
			/*
				c.setCarteBancaire(cv);
				System.out.println("La carte " + cv +" a été activée pour le compte " + c);
				*/
		}

	/**
	 *Supprimer la carte de son compte 
	 */

		@Override
		public void desactivationCarteVisa(Compte c, CarteBancaire cv) {
			idao.desactivationCarteVisa(c, cv);
			
			/*
				c.setCarteBancaire(null);
				System.out.println("La carte " + cv +" a été supprimée de compte " + c);
				*/
		}

	

	



}
