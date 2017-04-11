package metier;

import java.util.ArrayList;
import java.util.Collection;

public class Conseiller extends Personne {

	private Collection<Client> clients = new ArrayList<Client>();
	private Gerant gerant;
	private int idConseiller;
	
	
	

	public int getIdConseiller() {
		return idConseiller;
	}

	public void setIdConseiller(int idConseiller) {
		this.idConseiller = idConseiller;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public Collection<Client> getClients() {
		return clients;
	}

	public void setClients(Collection<Client> clients) {
		this.clients = clients;
	}

	public Conseiller(Collection<Client> clients, Gerant gerant) {
		super();
		this.clients = clients;
		this.gerant = gerant;
	}

	public Conseiller(Collection<Client> clients) {
		super();
		this.clients = clients;
	}


	/**
	 * Constructeur Conseiller sans argument
	 */
	public Conseiller() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructeur Conseiller avec 5 arguments
	 * @param nom Nom du conseiller
	 * @param prenom Prenom du conseiller
	 * @param telephone Téléphone du conseiller
	 * @param id Numéro d'identification du conseiller
	 * @param sonAdresse Adresse du conseiller (rue, code postale, ville) de la classe Adresse
	 */
	public Conseiller(String nom, String prenom, String telephone, int id, Adresse sonAdresse) {
		super(nom, prenom, telephone, id, sonAdresse);
		// TODO Auto-generated constructor stub
	}
	//Afficher Conseiller
	@Override
	public String toString() {
		return "Conseiller [ getSonAdresse()=" + getSonAdresse() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getTelephone()=" + getTelephone() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}
	
	
}
