package usagers;

public class Abonné {

	private int numéro;
	private String nom;
	
	public Abonné(int numéro, String nom){
		
		this.numéro = numéro;
		this.nom = nom;
	}

	public int getNuméro() {
		return numéro;
	}

	public String getNom() {
		return nom;
	}
}
