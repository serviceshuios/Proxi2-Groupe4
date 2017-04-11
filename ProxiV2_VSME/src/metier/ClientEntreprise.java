package metier;

import java.util.Collection;

public class ClientEntreprise extends Client {

	public ClientEntreprise(Collection<Compte> comptes, Placement placement,
			Conseiller conseiller) {
		super(comptes, placement, conseiller);
		// TODO Auto-generated constructor stub
	}

	public ClientEntreprise() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClientEntreprise ";
	}

}
