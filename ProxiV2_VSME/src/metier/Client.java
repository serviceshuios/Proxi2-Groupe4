package metier;

import java.util.ArrayList;
import java.util.Collection;

public class Client extends Personne {
	
	private Collection<Compte> comptes = new ArrayList<Compte>();
	private Placement placement;
	private Collection<Credit> credits = new ArrayList<Credit>();
	private Conseiller conseiller;
	private String typeClient; //particulier si client Particulier et entreprise si client Entreprise
	private int idClient;
	



	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}

	/**
	 * Constructeur de Client auquel est associé à la classe Personne et son adresse
	 * @param nom 	Correspond au nom du client
	 * @param prenom 	Correspond au prenom du client
	 * @param telephone Correspond au téléphone de la client
	 * @param id	Correspond au numéro d'identification du client
	 * @param sonAdresse Correspond à l'adresse du client
	 */
	
	public Client(String nom, String prenom, String telephone, int id, Adresse sonAdresse, String typeClient) {
		super(nom, prenom, telephone, id, sonAdresse);
		// TODO Auto-generated constructor stub
	}

	public Client(String nom, String prenom, String telephone, int id, Adresse sonAdresse) {
		super(nom, prenom, telephone, id, sonAdresse);
		// TODO Auto-generated constructor stub
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}

	public Collection<Credit> getCredits() {
		return credits;
	}

	public void setCredits(Collection<Credit> credits) {
		this.credits = credits;
	}

	
	
	public Client(Collection<Compte> comptes, Placement placement,  Conseiller conseiller) {
		super();
		this.comptes = comptes;
		this.placement = placement;
		this.conseiller = conseiller;
	}

	public Placement getPlacement() {
		return placement;
	}

	public void setPlacement(Placement placement) {
		this.placement = placement;
	}

	public Collection<Credit> getcredits() {
		return credits;
	}

	public void setcredits(Collection<Credit> credits) {
		this.credits = credits;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	
	

	public Client() {
		super();
	}



}
