package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IConseiller;
import service.Services;

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

		// contrôle authentification par session
		
		HttpSession session = request.getSession(false);
		// si pas de session, on en initialise une toute neuve avc deux attributs
		if (session == null || session.getAttribute("login") == null || session.getAttribute("attemptsCount") == null) {
			session = request.getSession();
			session.setAttribute("login", "visiteur");
			session.setAttribute("attemptsCount", 0);
		} else {
			// si déjà une session, on augmente le compteur de fail (si appui sur bouton valider de authenticate.jsp)
			if (request.getParameter("validauthenticate")!=null) {
				int i = ((int) session.getAttribute("attemptsCount")) + 1;
				session.setAttribute("attemptsCount", i);
			}
		}

		// si session n'est pas un conseiller, on verifie s'il y a un login
		if (!session.getAttribute("login").equals("Conseiller")) {
			if (request.getParameter("id") != null && request.getParameter("pwd") != null) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				// IConseiller ic = new Services();
				// if (ic.authentificationConseiller(id, pwd) == true) {
				// if ci-dessous à remplacer par le if ci-dessus
				if (id.equals("bourne") && pwd.equals("bourne")) {
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
			IConseiller ic = new Services();

			// ic.ajouterClient(co, c);

			request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
		}
		
		// Modifier un client
		if (request.getParameter("action").equals("ModifierClient")) {
			IConseiller ic = new Services();

			//ic.modifierClient(c, nom, prenom, a, email);;

			request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
		}

		// Effectuer un virement
		if (request.getParameter("action").equals("EffectuerVirement")) {
			IConseiller ic = new Services();

			// ic.effectuerVirement(montant, c1, c2);

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
