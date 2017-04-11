package metier;


/**
 * Métier CarteBancaire
 * Carte bancaire proposée aux Client.
 * Est associé un numéro d'identifiant de la carte et un compte
 * @author ME VS
 *
 */

public class CarteBancaire  {
	
	private Compte compte;
	private int idCarte;
	private String typeCarte;// CarteVisa soit Electron soit Premier

	public CarteBancaire(Compte compte) {
		super();
		this.compte = compte;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString() {
		return "CarteBancaire [compte=" + compte + "]";
	}
	
	/**
	 * Constructeur à 2 arguments
	 * @param idCarte Identifiant de la Carte
	 * @param typeCarte Type de carte visa (Electron ou Premier)
	 */
	public CarteBancaire(int idCarte, String typeCarte) {
		super();
		this.idCarte = idCarte;
		this.typeCarte = typeCarte;
	}
	
	public int getIdCarte() {
		return idCarte;
	}


	public void setIdCarte(int idCarte) {
		this.idCarte = idCarte;
	}
	public String getTypeCarte() {
		return typeCarte;
	}
	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}

	
}
