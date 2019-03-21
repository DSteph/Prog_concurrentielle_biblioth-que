package ressources;

import java.util.ArrayList;

import usagers.Abonn�;

public class Biblioth�que {

	private static ArrayList<Document> listeLivre;
	private static ArrayList<Abonn�> listeAbonn�;

	public static void setList(ArrayList<Document> listeLivre2, ArrayList<Abonn�> listeAbonn�2) {

		listeLivre = listeLivre2;
		listeAbonn� = listeAbonn�2;
	}

	public static boolean numLivreCorrect(int num�roLivre) {

		return num�roLivre <= listeLivre.size();
	}
	
	public static boolean numAboCorrect(int num�roAbonn�) {
		
		return num�roAbonn� <= listeAbonn�.size();
	}

	public static String r�server(int num�roAbonn�, int num�roLivre) {
		try {
			listeLivre.get(num�roLivre - 1).reserver(listeAbonn�.get(num�roAbonn� - 1));// on soustrait 1 pour atteindre
																						// le bon indice dans la liste
			return "Ouvrage r�serv� avec succ�s.";
		} catch (PasLibreException e) {
			return e.toString();
		}
	}

	public static String emprunter(int num�roAbonn�, int num�roLivre) {
		try {
			listeLivre.get(num�roLivre - 1).emprunter(listeAbonn�.get(num�roAbonn� - 1));
			return "Ouvrage emprunt� avec succ�s.";
		} catch (PasLibreException e) {
			return e.toString();
		}
	}

	public static String retour(int num�roLivre) {

		try {
		listeLivre.get(num�roLivre - 1).retour();
		return "Ouvrage retourn� avec succ�s.";
		}
		catch (RuntimeException e) {
			return e.toString();
		}
	}
}
