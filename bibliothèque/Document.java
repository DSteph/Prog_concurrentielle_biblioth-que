package bibliothèque;

import exceptions.PasLibreException;
import usagers.Abonné;

public interface Document {
	int numero();

	void reserver(Abonné ab) throws PasLibreException;

	void emprunter(Abonné ab) throws PasLibreException;

	void retour(); // document rendu ou annulation réservation
}
