package ressources;

@SuppressWarnings("serial")
public class NumLivInvalideException extends RuntimeException{

	public String toString() {
		
		return "Le numéro de livre saisi est invalide";
	}
	
}
