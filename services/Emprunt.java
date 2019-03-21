package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import biblioth�que.Biblioth�que;
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
				"Service emprunt - Veuillez entrer votre num�ro d'abonn�, puis le num�ro du livre selon le sch�ma X:Y");
		while (true) {
			try {
				reader = socketIn.readLine(); //Lecture de l'entr�e utilisateur
				String[] req = reader.split(":");//On s�pare le num�ro de livre et le num�ro d'abonn�
				numAb = Integer.parseInt(req[0]); //Conversion de l'entr�e utilisateur en int
				numLivre = Integer.parseInt(req[1]); 
				if (reader == "") //Une entr�e vide entraine une fermeture du socket
					SServ.close();
				else if (Biblioth�que.numAboCorrect(numAb) && Biblioth�que.numLivreCorrect(numLivre)) {
					socketOut.println(Biblioth�que.emprunter(numAb, numLivre)
							+ " Veuillez formuler une nouvelle demande ou commande vide pour quitter");
				} else
					socketOut.println("Emprunt impossible, num�ro Abonn� / Livre incorrect");
			} catch (PasLibreException e1) {
				socketOut.println(e1.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
				socketOut.println("Exception serveur");
			}
		}
	}
}
