package exceptions;

@SuppressWarnings("serial")
public class PasLibreException extends RuntimeException {

	public String toString() {

		return "Le document demand� n'est pas disponible";
	}
}
