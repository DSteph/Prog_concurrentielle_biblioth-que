package biblioth�que;

import exceptions.PasLibreException;
import usagers.Abonn�;

public interface Document {
	int numero();

	void reserver(Abonn� ab) throws PasLibreException;

	void emprunter(Abonn� ab) throws PasLibreException;

	void retour(); // document rendu ou annulation r�servation
}
