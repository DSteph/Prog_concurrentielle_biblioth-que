package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.Emprunt;

public class Serveur_emprunt implements Runnable {

	ServerSocket empruntServ;

	public Serveur_emprunt(int port) throws IOException {
		empruntServ = new ServerSocket(port);
	}

	@Override
	public void run() {
		System.out.println("Serveur_emprunt en attente d'une requête");
		try {
			new Thread(new Emprunt(empruntServ.accept())).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
