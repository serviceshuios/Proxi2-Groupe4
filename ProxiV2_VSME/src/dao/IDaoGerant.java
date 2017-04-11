package dao;

import metier.Adresse;
import metier.Agence;
import metier.Conseiller;
import metier.Gerant;

public interface IDaoGerant {
	
	public String effectuerAudit(Agence agence);
	public void AjouterConseiller(Gerant g, Conseiller co);
	public void SupprimerConseiller(Conseiller c, Gerant g);
	public void AfficherConseiller(Conseiller c);
	void ModifierConseiller(Conseiller c, Adresse a, String telephone);

}
