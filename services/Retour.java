package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import biblioth�que.Biblioth�que;

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
		socketOut.println("Service retour - Veuillez entrer le num�ro du livre � retourner");
		while (true) {
			try {
				reader = socketIn.readLine();
				numLivre = Integer.parseInt(reader);
				if (reader == "")
					SServ.close();
				else if (Biblioth�que.numLivreCorrect(numLivre)) {
					socketOut.println(Biblioth�que.retour(numLivre)
							+ " Veuillez formuler une nouvelle demande ou commande vide pour quitter");
				} else
					socketOut.println("Num�ro de Livre incorrect");
			} catch (IOException e1) {
				e1.printStackTrace();
				socketOut.println("Exception serveur");
			}
		}
	}
}
