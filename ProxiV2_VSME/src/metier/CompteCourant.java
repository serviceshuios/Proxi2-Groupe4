package metier;

public class CompteCourant extends Compte {
	
	private double decouvert;
	

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public CompteCourant(int numeroCompte, double solde, String dateOuverture, Client client, CarteBancaire carteBancaire,
			double decouvert) {
		super(numeroCompte, solde, dateOuverture, client, carteBancaire);
		this.decouvert = decouvert;
	}

	public CompteCourant() {
		super();
		this.decouvert = 1000;
	}

	@Override
	public String toString() {
		return "CompteCourant [decouvert=" + decouvert + ", toString()=" + super.toString() + "]";
	}



	

	

}
