package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import services.R�servation;

public class Serveur_r�servation implements Runnable {

	ServerSocket resaServ;

	public Serveur_r�servation(int port) throws IOException {
		resaServ = new ServerSocket(port);
	}

	@Override
	public void run() {
		System.out.println("Serveur_r�servation en attente d'une requ�te");
		try {
			new Thread(new R�servation(resaServ.accept())).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
