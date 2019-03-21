package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliothèque.Bibliothèque;

public class Retour implements Runnable {

	String reader;
	int numLivre;
	Socket SServ;
	BufferedReader socketIn;
	PrintWriter socketOut;

	public Retour(Socket SServ) throws IOException {

		this.SServ = SServ;
		socketIn = new BufferedReader(new InputStreamReader(SServ.getInputStream()));
		socketOut = new PrintWriter(SServ.getOutputStream(), true);
	}

	@Override
	public void run() {
		socketOut.println("Service retour - Veuillez entrer le numéro du livre à retourner");
		while (true) {
			try {
				reader = socketIn.readLine();
				numLivre = Integer.parseInt(reader);
				if (reader == "")
					SServ.close();
				else if (Bibliothèque.numLivreCorrect(numLivre)) {
					socketOut.println(Bibliothèque.retour(numLivre)
							+ " Veuillez formuler une nouvelle demande ou commande vide pour quitter");
				} else
					socketOut.println("Numéro de Livre incorrect");
			} catch (IOException e1) {
				e1.printStackTrace();
				socketOut.println("Exception serveur");
			}
		}
	}
}
