/**
 * 
 */
package metier;

import java.util.Collection;

/**
 * Metier Localisation
 * Classe associée à un Placement et les villes des places boursières
 * @author ME VS
 *
 */
public class Localisation {

	private Collection <Placement> placementsLocalisation;

	
	public Collection<Placement> getPlacementsLocalisation() {
		return placementsLocalisation;
	}

	
	public void setPlacementsLocalisation(Collection<Placement> placementsLocalisation) {
		this.placementsLocalisation = placementsLocalisation;
	}
	
	
}
