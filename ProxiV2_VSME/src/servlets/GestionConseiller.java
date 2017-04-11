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

		// controle authentification
		HttpSession session = request.getSession(false);
		// si pas de session on en initialise une
		if (session == null || session.getAttribute("login") == null || session.getAttribute("attemptsCount") == null) {
			session = request.getSession();
			session.setAttribute("login", "visiteur");
			session.setAttribute("attemptsCount", 0);
		} else {
			// si déjà une, on augmente le compteur de fail
			int i = ((int) session.getAttribute("attemptsCount")) + 1;
			session.setAttribute("attemptsCount", i);
		}
		// si session pas conseiller, on verifie le login
		if (!session.getAttribute("login").equals("Conseiller")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			IConseiller ic = new Services();
			if (ic.authentificationConseiller(id, pwd) == true) {
				session.setAttribute("login", "Conseiller");
				session.setAttribute("attemptsCount", 0);
			} else {
				request.getRequestDispatcher("/Authenticate.jsp").forward(request, response);
			}
		}

		if (request.getParameter("action").equals("Deconnection")) {
			session.setAttribute("login", "visiteur");
			request.getRequestDispatcher("/interfaceConseiller.jsp").forward(request, response);
		}
		
		if (request.getParameter("action").equals("interfaceConseiller")) {
			IConseiller ic = new Services();
			
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
