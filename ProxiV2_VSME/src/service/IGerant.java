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
	public void ajouterConseiller(int idge, Conseiller co);
	public void supprimerConseiller(Conseiller c, int idge);
	public void listerConseiller(int idge);
	public void modifierConseiller(Conseiller c, Adresse a, String telephone);
	
}
