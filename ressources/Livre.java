package ressources;

import bibliothèque.Document;
import exceptions.PasLibreException;
import usagers.Abonné;

public class Livre implements Document {

	private int numéro;
	private String titre;
	private boolean réservé;
	private boolean emprunté;
	private Thread T;
	private Abonné détenteur;

	public Livre(int numéro, String titre) {
		this.numéro = numéro;
		this.titre = titre;
		this.réservé = false;
		this.emprunté = false;
		T = null;
	}

	@Override
	public int numero() {
		return numéro;
	}

	@Override
	public void reserver(Abonné ab) throws PasLibreException {
		synchronized (this) {
			if (réservé || emprunté)
				throw new PasLibreException();
			else {
				this.détenteur = ab;
				this.réservé = true;
				this.T = new Thread(new TimerResa(this));
				T.start();
			}
		}
	}

	@Override
	public void emprunter(Abonné ab) throws PasLibreException {
		synchronized (this) {
			if (réservé && ab.getNuméro() == détenteur.getNuméro()) {
				T.interrupt();
				this.emprunté = true;
				this.réservé = false;
			} else if (emprunté || réservé)
				throw new PasLibreException();
			else
				emprunté = true;
		}
	}

	@Override
	public void retour() {
		synchronized (this) {
			this.emprunté = false;
			this.réservé = false;
			this.détenteur = null;
		}
	}

	public String toString() {
		return "Numéro: " + numéro + " - Titre: " + titre;
	}

	public int getNuméro() {
		return this.numéro;
	}
}
