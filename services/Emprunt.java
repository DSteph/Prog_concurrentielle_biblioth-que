package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliothèque.Bibliothèque;
import exceptions.PasLibreException;

public class Emprunt implements Runnable {

	String reader;
	int numAb, numLivre;
	Socket SServ;
	BufferedReader socketIn;
	PrintWriter socketOut;

	public Emprunt(Socket SServ) throws IOException {

		this.SServ = SServ;
		socketIn = new BufferedReader(new InputStreamReader(SServ.getInputStream()));
		socketOut = new PrintWriter(SServ.getOutputStream(), true);
	}

	@Override
	public void run() {
		socketOut.println(
				"Service emprunt - Veuillez entrer votre numéro d'abonné, puis le numéro du livre selon le schéma X:Y");
		while (true) {
			try {
				reader = socketIn.readLine(); //Lecture de l'entrée utilisateur
				String[] req = reader.split(":");//On sépare le numéro de livre et le numéro d'abonné
				numAb = Integer.parseInt(req[0]); //Conversion de l'entrée utilisateur en int
				numLivre = Integer.parseInt(req[1]); 
				if (reader == "") //Une entrée vide entraine une fermeture du socket
					SServ.close();
				else if (Bibliothèque.numAboCorrect(numAb) && Bibliothèque.numLivreCorrect(numLivre)) {
					socketOut.println(Bibliothèque.emprunter(numAb, numLivre)
							+ " Veuillez formuler une nouvelle demande ou commande vide pour quitter");
				} else
					socketOut.println("Emprunt impossible, numéro Abonné / Livre incorrect");
			} catch (PasLibreException e1) {
				socketOut.println(e1.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
				socketOut.println("Exception serveur");
			}
		}
	}
}
