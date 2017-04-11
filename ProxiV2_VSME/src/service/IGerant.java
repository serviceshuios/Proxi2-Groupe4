package service;

import java.util.Collection;

import metier.Adresse;
import metier.Agence;
import metier.Client;
import metier.Conseiller;
import metier.Gerant;
import service.exception.AuditNegatifException;

public interface IGerant {

	public String effectuerAudit(Agence agence);
	public void ajouterConseiller(Gerant g, Conseiller co);
	public void supprimerConseiller(Conseiller c, Gerant g);
	public void afficherConseiller(Conseiller c);
	public void modifierConseiller(Conseiller c, Adresse a, String telephone);
	
}
