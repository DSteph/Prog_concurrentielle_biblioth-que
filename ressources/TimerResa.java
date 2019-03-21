package ressources;

import java.util.TimerTask;

public class TimerResa extends TimerTask {

	long tempsResa;
	Livre L;

	public TimerResa(Livre L) {

		this.L = L;
		tempsResa = 7200000;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(tempsResa);
			L.retour();
		} catch (InterruptedException e) {
			System.out.println("Timer de réservation interrompu");
			;
		}
	}
}
