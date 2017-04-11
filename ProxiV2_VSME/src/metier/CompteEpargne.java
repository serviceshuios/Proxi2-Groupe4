package metier;

public class CompteEpargne extends Compte {
	private double remuneration;
	
	

	public double getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(double remuneration) {
		this.remuneration = remuneration;
	}

	public CompteEpargne(int numeroCompte, double solde, String dateOuverture, Client client, CarteBancaire carteBancaire,
			double remuneration) {
		super(numeroCompte, solde, dateOuverture, client, carteBancaire);
		this.remuneration = remuneration;
	}

	public CompteEpargne() {
		super();
		this.remuneration = 0.03;
	}

	@Override
	public String toString() {
		return "CompteEpargne [remuneration=" + remuneration + ", toString()=" + super.toString() + "]";
	}


	
	

}
