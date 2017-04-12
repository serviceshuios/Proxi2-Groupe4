package servlets;

import java.io.IOException;

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

		// contrôle authentification par session

		// !!!!!!!!!!!! faut ajouter l'id conseiller à la session !!!!!!!!!!!!

		// si pas de session, on en initialise une toute neuve avc deux
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
		// fin contrôle authentification

		// déconnection
		if (request.getParameter("action").equals("Deconnection")) {
			session.invalidate();
			request.getRequestDispatcher("/Authenticate.jsp").forward(request, response);
		}

		// Ajouter un client
		if (request.getParameter("action").equals("AjouterClient")) {
			// initialisation parametre de controle saisie
			boolean validform = true;

			// si appui sur le bouton de validation formulaire
			if (request.getParameter("validerajouterclient") != null) {

				// vérification formulaire
				if (request.getParameter("nom") == null || request.getParameter("nom").equals("")) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}
				if (request.getParameter("prenom") == null || request.getParameter("prenom").equals("")) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}
				if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}
				if (request.getParameter("telephone") == null || request.getParameter("telephone").equals("")) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}
				if (request.getParameter("adresse") == null || request.getParameter("adresse").equals("")) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}
				if (request.getParameter("codepostal") == null || request.getParameter("codepostal").equals("")) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}
				if (request.getParameter("ville") == null || request.getParameter("ville").equals("")) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}
				if (request.getParameter("typeclient") == null
						|| (!request.getParameter("typeclient").equals("particulier")
								&& !request.getParameter("typeclient").equals("entreprise"))) {
					// request.setAttribute("validerajouterclientdefaut", pb);
					validform = false;
				}

				// si une erreur de formulaire, on renvoie sur le formulaire
				if (validform == false) {
					request.setAttribute("validerajouterclientdefaut", "pb");
					request.getRequestDispatcher("/AjouterClient.jsp").forward(request, response);
				} else {
					// sinon en envoie le tout en base de données
					Client c = new Client();
					c.setNom(request.getParameter("nom"));
					c.setPrenom(request.getParameter("prenom"));
					c.setEmail(request.getParameter("email"));
					c.setTelephone(request.getParameter("telephone"));
					Adresse adr = new Adresse();
					adr.setAdresse(request.getParameter("adresse"));
					adr.setCodePostale((Integer.parseInt(request.getParameter("codepostal"))));
					adr.setVille(request.getParameter("adresse"));
					c.setSonAdresse(adr);
					c.setTypeClient(request.getParameter("typeclient"));

					switch (request.getParameter("typeclient")) {
					case "clientparticulier":
						c = (ClientParticulier) c;
						break;
					case "cliententreprise":
						c = (ClientEntreprise) c;
						break;
					default:
						break;
					}

					IConseiller ic = new Services();
					try {
						ic.ajouterClient(Integer.parseInt(session.getAttribute("idConseiller").toString()), c);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LeConseillerADeja10Clients e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("resultatvalidation", "Nouveau client ajouté");
					request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
				}
			}
			// par défaut, on envoie sur le formulaire
			request.getRequestDispatcher("/AjouterClient.jsp").forward(request, response);
		}

		// Modifier un client
		if (request.getParameter("action").equals("ModifierClient"))

		{
			IConseiller ic = new Services();

			// ic.modifierClient(c, nom, prenom, a, email);;

			request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
		}

		// Effectuer un virement
		if (request.getParameter("action").equals("EffectuerVirement")) {
			IConseiller ic = new Services();

			// ic.effectuerVirement(montant, c1, c2);

			request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
		}

		// interface conseiller
		if (request.getParameter("action").equals("interfaceConseiller")) {
			IConseiller ic = new Services();

			request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
		}

		// dans tous les autre cas on redirige vers l'authentification
		request.getRequestDispatcher("/Authenticate.jsp");

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
