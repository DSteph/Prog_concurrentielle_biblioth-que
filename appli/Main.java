package appli;

import java.io.IOException;
import java.util.ArrayList;

import bibliothèque.Bibliothèque;
import bibliothèque.Document;
import ressources.Livre;
import serveur.Serveur_emprunt;
import serveur.Serveur_retour;
import serveur.Serveur_réservation;
import usagers.Abonné;

public class Main {

	public static void main(String[] args) {

		ArrayList<Document> listeLivre = new ArrayList<Document>();
		ArrayList<Abonné> listeAbonné = new ArrayList<Abonné>();

		listeLivre.add(new Livre(1, "Gargentuel et Pentagrua"));
		listeLivre.add(new Livre(2, "Le Cidre"));
		listeLivre.add(new Livre(3, "Guère épais"));
		listeLivre.add(new Livre(4, "Bellamy"));
		listeLivre.add(new Livre(5, "La concurrence, une histoire de répartition, ou peut-être est-ce l'inverse"));

		listeAbonné.add(new Abonné(1, "Néron"));
		listeAbonné.add(new Abonné(2, "Michel Drucker"));
		listeAbonné.add(new Abonné(3, "Kurt Cobain"));
		listeAbonné.add(new Abonné(4, "Jean Roucas"));
		listeAbonné.add(new Abonné(5, "NKM"));

		Bibliothèque.setList(listeLivre, listeAbonné);

		try {
			new Thread(new Serveur_réservation(2500)).start();
			new Thread(new Serveur_emprunt(2600)).start();
			new Thread(new Serveur_retour(2700)).start();
		} catch (IOException e) {
			System.out.println("Erreur instanciation serveur");
			e.printStackTrace();
		}
	}
}
