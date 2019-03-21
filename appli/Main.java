package appli;

import java.io.IOException;
import java.util.ArrayList;

import biblioth�que.Biblioth�que;
import biblioth�que.Document;
import ressources.Livre;
import serveur.Serveur_emprunt;
import serveur.Serveur_retour;
import serveur.Serveur_r�servation;
import usagers.Abonn�;

public class Main {

	public static void main(String[] args) {

		ArrayList<Document> listeLivre = new ArrayList<Document>();
		ArrayList<Abonn�> listeAbonn� = new ArrayList<Abonn�>();

		listeLivre.add(new Livre(1, "Gargentuel et Pentagrua"));
		listeLivre.add(new Livre(2, "Le Cidre"));
		listeLivre.add(new Livre(3, "Gu�re �pais"));
		listeLivre.add(new Livre(4, "Bellamy"));
		listeLivre.add(new Livre(5, "La concurrence, une histoire de r�partition, ou peut-�tre est-ce l'inverse"));

		listeAbonn�.add(new Abonn�(1, "N�ron"));
		listeAbonn�.add(new Abonn�(2, "Michel Drucker"));
		listeAbonn�.add(new Abonn�(3, "Kurt Cobain"));
		listeAbonn�.add(new Abonn�(4, "Jean Roucas"));
		listeAbonn�.add(new Abonn�(5, "NKM"));

		Biblioth�que.setList(listeLivre, listeAbonn�);

		try {
			new Thread(new Serveur_r�servation(2500)).start();
			new Thread(new Serveur_emprunt(2600)).start();
			new Thread(new Serveur_retour(2700)).start();
		} catch (IOException e) {
			System.out.println("Erreur instanciation serveur");
			e.printStackTrace();
		}
	}
}
