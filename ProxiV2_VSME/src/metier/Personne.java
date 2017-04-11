package metier;

public class Personne {
	private String nom;
	private String prenom;
	private String telephone;
	private int id;
	private Adresse sonAdresse;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	

	/**
	 * Constructeur de Personne sans argument
	 */
	public Personne() {
		super();
	}
	/**
	 * Constructeur de Personne avec 5 arguments
	 * @param nom Nom de la personne
	 * @param prenom Prenom de la personne
	 * @param telephone Telephone de la personne
	 * @param id Numéro d'identifiant de la personne
	 * @param sonAdresse Adresse de la personne
	 */
	public Personne(String nom, String prenom, String telephone, int id, Adresse sonAdresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.id = id;
		this.setSonAdresse(sonAdresse);
	}

	public Adresse getSonAdresse() {
		return sonAdresse;
	}

	public void setSonAdresse(Adresse sonAdresse) {
		this.sonAdresse = sonAdresse;
	}

	
}
