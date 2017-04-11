package metier;

import java.util.ArrayList;
import java.util.Collection;

/** 
 * Métier Adresse
 * Correspond aux adresses de chaque personnes (Client, Conseiller, Gérant, Agence)
 * @author Vincent,Marine
 *
 */
public class Adresse {

	private String adresse;
	private int codePostale;
	private String ville;
	
	private int idAdresse;
	
	public int getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	private Collection <Personne> lesPersonnes  = new ArrayList();
	private Agence monAgence;
	
	
	
	public Collection<Personne> getLesPersonnes() {
		return lesPersonnes;
	}
	public void setLesPersonnes(Collection<Personne> lesPersonnes) {
		this.lesPersonnes = lesPersonnes;
	}
	public Agence getMonAgence() {
		return monAgence;
	}
	public void setMonAgence(Agence monAgence) {
		this.monAgence = monAgence;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}


	/**
	 * Constructeur à 3 arguments de l'Adresse
	 * @param adresse correspond au numéro et  nom de rue
	 * @param codePostale Correspond au Code postale de la ville
	 * @param ville Correspond au nom de la ville
	 */
	public Adresse(String adresse, int codePostale, String ville) {
		super();
		this.adresse = adresse;
		this.codePostale = codePostale;
		this.ville = ville;
	}
	
	/**
	 * Constructeur de l'Adresse sans argument
	 */
	public Adresse() {
		super();
	}
	
	//Affichage de la Classe Adresse
	@Override
	public String toString() {
		return "Adresse [adresse=" + adresse + ", codePostale=" + codePostale + ", ville=" + ville + "]";
	}
	
	
	
}
