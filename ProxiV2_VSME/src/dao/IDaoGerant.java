package dao;

import metier.Adresse;
import metier.Agence;
import metier.Conseiller;
import metier.Gerant;

public interface IDaoGerant {
	
	public String effectuerAudit(Agence agence);
	public void ajouterConseiller(Gerant g, Conseiller co);
	public void supprimerConseiller(Conseiller c, Gerant g);
	public void afficherConseiller(Conseiller c);
	void modifierConseiller(Conseiller c, Adresse a, String telephone);

}
