package ressources;

import biblioth�que.Document;
import exceptions.PasLibreException;
import usagers.Abonn�;

public class Livre implements Document {

	private int num�ro;
	private String titre;
	private boolean r�serv�;
	private boolean emprunt�;
	private Thread T;
	private Abonn� d�tenteur;

	public Livre(int num�ro, String titre) {
		this.num�ro = num�ro;
		this.titre = titre;
		this.r�serv� = false;
		this.emprunt� = false;
		T = null;
	}

	@Override
	public int numero() {
		return num�ro;
	}

	@Override
	public void reserver(Abonn� ab) throws PasLibreException {
		synchronized (this) {
			if (r�serv� || emprunt�)
				throw new PasLibreException();
			else {
				this.d�tenteur = ab;
				this.r�serv� = true;
				this.T = new Thread(new TimerResa(this));
				T.start();
			}
		}
	}

	@Override
	public void emprunter(Abonn� ab) throws PasLibreException {
		synchronized (this) {
			if (r�serv� && ab.getNum�ro() == d�tenteur.getNum�ro()) {
				T.interrupt();
				this.emprunt� = true;
				this.r�serv� = false;
			} else if (emprunt� || r�serv�)
				throw new PasLibreException();
			else
				emprunt� = true;
		}
	}

	@Override
	public void retour() {
		synchronized (this) {
			this.emprunt� = false;
			this.r�serv� = false;
			this.d�tenteur = null;
		}
	}

	public String toString() {
		return "Num�ro: " + num�ro + " - Titre: " + titre;
	}

	public int getNum�ro() {
		return this.num�ro;
	}
}
