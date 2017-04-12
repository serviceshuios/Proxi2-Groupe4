package servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.Adresse;
import metier.Client;
import metier.ClientEntreprise;
import metier.ClientParticulier;
import service.IConseiller;
import service.Services;
import service.exception.LeConseillerADeja10Clients;

/**
 * Servlet implementation class GestionClients
 */
@WebServlet("/GestionConseiller")
public class GestionConseiller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionConseiller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		/**
		 * contrôle authentification par session
		 */
		// si pas de session, on en initialise une toute neuve avec deux
		// attributs
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("login") == null || session.getAttribute("attemptsCount") == null) {
			session = request.getSession();
			session.setAttribute("login", "visiteur");
			session.setAttribute("attemptsCount", 0);
		} else {
			// si déjà une session, on augmente le compteur de fail (si appui
			// sur bouton valider de authenticate.jsp)
			if (request.getParameter("validauthenticate") != null) {
				session.setAttribute("attemptsCount", ((int) session.getAttribute("attemptsCount")) + 1);
			}
		}

		// si session n'est pas un conseiller, on verifie s'il y a un login
		if (!session.getAttribute("login").equals("Conseiller")) {
			if (request.getParameter("id") != null && request.getParameter("pwd") != null) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				IConseiller ic = new Services();
				int idCons = ic.authentificationConseiller(id, pwd);
				if (idCons != 0) {
					// if (id.equals("bourne") && pwd.equals("bourne")) {
					session.setAttribute("idConseiller", idCons);
					session.setAttribute("login", "Conseiller");
					session.setAttribute("attemptsCount", 0);
				} else {
					request.getRequestDispatcher("/Authenticate.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("/Authenticate.jsp").forward(request, response);
			}
		}

		/**
		 * déconnection
		 */
		if (request.getParameter("action").equals("Deconnection")) {
			session.invalidate();
			request.getRequestDispatcher("/Authenticate.jsp").forward(request, response);
		}

		if (request.getParameter("action").equals("interfaceConseiller")) {

			/**
			 * listes clients pour l'interface conseiller
			 */
			IConseiller ic1 = new Services();
			Collection<Client> colcli1 = ic1
					.listerClient(Integer.parseInt(session.getAttribute("idConseiller").toString()));
			request.setAttribute("listeclients", colcli1);

			/**
			 * Ajouter un client
			 */
			if (request.getParameter("ajouterclient") != null || request.getParameter("validerajouterclient") != null) {

				// si appui sur le bouton de validation formulaire
				if (request.getParameter("validerajouterclient") != null) {

					// initialisation paramètre de controle saisie
					boolean validform = true;

					// vérification formulaire
					if (request.getParameter("nom") == null || request.getParameter("nom").equals("")) {
						validform = false;
					}
					if (request.getParameter("prenom") == null || request.getParameter("prenom").equals("")) {
						validform = false;
					}
					if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
						validform = false;
					}
					if (request.getParameter("telephone") == null || request.getParameter("telephone").equals("")) {
						validform = false;
					}
					if (request.getParameter("adresse") == null || request.getParameter("adresse").equals("")) {
						validform = false;
					}
					if (request.getParameter("codepostal") == null || request.getParameter("codepostal").equals("")) {
						validform = false;
					}
					if (request.getParameter("ville") == null || request.getParameter("ville").equals("")) {
						validform = false;
					}
					if (request.getParameter("typeclient") == null
							|| (!request.getParameter("typeclient").equals("particulier")
									&& !request.getParameter("typeclient").equals("entreprise"))) {
						validform = false;
					}

					// si une erreur de formulaire, on renvoie sur le formulaire
					if (validform == false) {
						request.setAttribute("validerajouterclientdefaut", "pb");
						request.getRequestDispatcher("/AjouterClient.jsp").forward(request, response);
					} else {
						// sinon en envoie le tout en base de données
						if (request.getParameter("typeclient").equals("particulier")) {
							ClientParticulier c = new ClientParticulier();
							c.setNom(request.getParameter("nom"));
							c.setPrenom(request.getParameter("prenom"));
							c.setEmail(request.getParameter("email"));
							c.setTelephone(request.getParameter("telephone"));
							Adresse adr = new Adresse();
							adr.setAdresse(request.getParameter("adresse"));
							adr.setCodePostale((Integer.parseInt(request.getParameter("codepostal"))));
							adr.setVille(request.getParameter("ville"));
							c.setSonAdresse(adr);
							c.setTypeClient(request.getParameter("typeclient"));
							IConseiller ic = new Services();
							try {
								ic.ajouterClient(Integer.parseInt(session.getAttribute("idConseiller").toString()), c);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (LeConseillerADeja10Clients e) {
								// TODO Auto-generated catch block
								request.setAttribute("resultatvalidation",
										"vous avez atteint votre maximum de clients");
								request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
							}
						} else if(request.getParameter("typeclient").equals("entreprise")) {
							ClientEntreprise c = new ClientEntreprise();
							c.setNom(request.getParameter("nom"));
							c.setPrenom(request.getParameter("prenom"));
							c.setEmail(request.getParameter("email"));
							c.setTelephone(request.getParameter("telephone"));
							Adresse adr = new Adresse();
							adr.setAdresse(request.getParameter("adresse"));
							adr.setCodePostale((Integer.parseInt(request.getParameter("codepostal"))));
							adr.setVille(request.getParameter("ville"));
							c.setSonAdresse(adr);
							c.setTypeClient(request.getParameter("typeclient"));
							IConseiller ic = new Services();
							try {
								ic.ajouterClient(Integer.parseInt(session.getAttribute("idConseiller").toString()), c);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (LeConseillerADeja10Clients e) {
								// TODO Auto-generated catch block
								request.setAttribute("resultatvalidation",
										"vous avez atteint votre maximum de clients");
								request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
							}
						}
						request.setAttribute("resultatvalidation", "Nouveau client ajouté");
						request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
					}
				}
				// par défaut, on envoie sur le formulaire
				request.getRequestDispatcher("/AjouterClient.jsp").forward(request, response);
			}

			/**
			 * Modifier un client
			 */
			if (request.getParameter("modifierclient") != null) {
				
				if (request.getParameter("idclientform") != null) {
					// par défaut, on envoie sur le formulaire avec le client
					IConseiller ic = new Services();
					int idclientform = Integer.parseInt(request.getParameter("idclientform").toString());
					Collection<Client> colcli = ic
							.listerClient(Integer.parseInt(session.getAttribute("idConseiller").toString()));
					for (Client client : colcli) {
						if (client.getIdClient() == idclientform) {
							request.setAttribute("client", client);
							request.setAttribute("clientadresse", client.getSonAdresse());
						}
					}
					request.getRequestDispatcher("/ModifierClient.jsp").forward(request, response);
				}
				request.setAttribute("resultatvalidation", "veuillez choisir un client dans la liste");
				request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
			}

			

				// si appui sur le bouton de validation formulaire
				if (request.getParameter("validerModifierclient") != null) {

					// initialisation parametre de controle saisie
					boolean validform = true;
					// vérification formulaire
					if (request.getParameter("nom") == null || request.getParameter("nom").equals("")) {
						validform = false;
					}
						else{
					if (request.getParameter("prenom") == null || request.getParameter("prenom").equals("")) {
						validform = false;
					}
					else{
					if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
						validform = false;
					}
					else{
					if (request.getParameter("telephone") == null || request.getParameter("telephone").equals("")) {
						validform = false;
					}
					else{
					if (request.getParameter("adresse") == null || request.getParameter("adresse").equals("")) {
						validform = false;
					}
					else{
					if (request.getParameter("codepostal") == null || request.getParameter("codepostal").equals("")) {
						validform = false;
					}
					else{
					if (request.getParameter("ville") == null || request.getParameter("ville").equals("")) {
						validform = false;
					}
					//else{
					//if (request.getParameter("idclientform") == null
							//|| request.getParameter("idclientform").equals("")) {
						//validform = false;
					//}
					else{
					// si une erreur de formulaire, on renvoie sur le formulaire
					if (validform == false) {
						request.setAttribute("validerModifierclientdefaut", "pb");
						request.getRequestDispatcher("/ModifierClient.jsp").forward(request, response);
					} 
					else {
						// sinon on envoie le tout en base de données
						IConseiller ic = new Services();
						int idclientform = Integer.parseInt(request.getParameter("idclientform").toString());
						Collection<Client> colcli = ic
								.listerClient(Integer.parseInt(session.getAttribute("idConseiller").toString()));
						for (Client client : colcli) {
							if (client.getIdClient() == idclientform) {
								Adresse adr = new Adresse();
								adr.setAdresse(request.getParameter("adresse"));
								adr.setCodePostale((Integer.parseInt(request.getParameter("codepostal"))));
								adr.setVille(request.getParameter("ville"));
								ic.modifierClient(client, request.getParameter("nom"), request.getParameter("prenom"),
										adr, request.getParameter("email"));
							}
						}
						request.setAttribute("resultatvalidation", "client modifié");
						request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
					}
					}}}}}}}
				}
				
			/**
			 * Effectuer un virement
			 */
			if (request.getParameter("action").equals("EffectuerVirement"))

			{
				IConseiller ic = new Services();

				// ic.effectuerVirement(montant, c1, c2);

				request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
			}
			request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
		}
		/*
		 * if (request.getParameter("action").equals("ajouter")) { // 1 -
		 * récupérer paramètres (du formulaire) String nom =
		 * request.getParameter("nom"); String prenom =
		 * request.getParameter("prenom"); String couleuryeux =
		 * request.getParameter("couleurYeux"); int age =
		 * Integer.parseInt(request.getParameter("age"));
		 * 
		 * // 2 - TTT avec couches services // IClientService ic = new
		 * ClientService(); // Client c = new Client(nom, prenom, couleuryeux,
		 * age); // ic.ajouterClient(c);
		 * 
		 * // 3 - préparation envoi request.setAttribute("lenom", nom); // 1er
		 * param = nom attribut dans // jsp, 2nd param = variable du //
		 * getParameter request.setAttribute("leprenom", prenom);
		 * request.setAttribute("lacouleuryeux", couleuryeux);
		 * request.setAttribute("lage", age);
		 * 
		 * // 4 - envoi
		 * request.getRequestDispatcher("/resultatAjouterClient.jsp").forward(
		 * request, response); ; }
		 * 
		 * if (request.getParameter("action").equals("lister")) { } if
		 * (request.getParameter("action").equals("supprimer")) { } if
		 * (request.getParameter("action").equals("modifier")) { }
		 */
		// création ou modification des attributs de session
		// session.setAttribute("lelogin", login);

		// accès aux attributs de session
		// session.getAttribute("lelogin");

		// destruction de la session
		// session.invalidate();

		// traitement se terminant par
		// request.getRequestDispatcher("/resultatAjouterClient.jsp").forward(request,
		// response);

	}

}
