package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.Réservation;

public class Serveur_réservation implements Runnable {

	ServerSocket resaServ;

	public Serveur_réservation(int port) throws IOException {
		resaServ = new ServerSocket(port);
	}

	@Override
	public void run() {
		System.out.println("Serveur_réservation en attente d'une requête");
		try {
			new Thread(new Réservation(resaServ.accept())).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
