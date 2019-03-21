package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.Retour;

public class Serveur_retour implements Runnable {

	ServerSocket retServ;

	public Serveur_retour(int port) throws IOException {
		retServ = new ServerSocket(port);
	}

	@Override
	public void run() {
		System.out.println("Serveur_retour en attente d'une requête");
		try {
			new Thread(new Retour(retServ.accept())).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
