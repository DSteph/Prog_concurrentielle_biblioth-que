package ressources;

import java.util.ArrayList;

import usagers.Abonné;

public class Bibliothèque {

	private static ArrayList<Document> listeLivre;
	private static ArrayList<Abonné> listeAbonné;

	public static void setList(ArrayList<Document> listeLivre2, ArrayList<Abonné> listeAbonné2) {

		listeLivre = listeLivre2;
		listeAbonné = listeAbonné2;
	}

	public static boolean numLivreCorrect(int numéroLivre) {

		return numéroLivre <= listeLivre.size();
	}
	
	public static boolean numAboCorrect(int numéroAbonné) {
		
		return numéroAbonné <= listeAbonné.size();
	}

	public static String réserver(int numéroAbonné, int numéroLivre) {
		try {
			listeLivre.get(numéroLivre - 1).reserver(listeAbonné.get(numéroAbonné - 1));// on soustrait 1 pour atteindre
																						// le bon indice dans la liste
			return "Ouvrage réservé avec succès.";
		} catch (PasLibreException e) {
			return e.toString();
		}
	}

	public static String emprunter(int numéroAbonné, int numéroLivre) {
		try {
			listeLivre.get(numéroLivre - 1).emprunter(listeAbonné.get(numéroAbonné - 1));
			return "Ouvrage emprunté avec succès.";
		} catch (PasLibreException e) {
			return e.toString();
		}
	}

	public static String retour(int numéroLivre) {

		try {
		listeLivre.get(numéroLivre - 1).retour();
		return "Ouvrage retourné avec succès.";
		}
		catch (RuntimeException e) {
			return e.toString();
		}
	}
}
