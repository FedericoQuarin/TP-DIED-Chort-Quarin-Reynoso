package excepciones;

public class InputInvalidaException extends Exception {
	
	public InputInvalidaException() {
		super("Se han ingresado uno o m�s datos inv�lidos.\n"+
			  "Por favor, revise y vuelva a intentar.");
	}

}
