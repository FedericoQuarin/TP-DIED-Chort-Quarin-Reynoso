package excepciones;

public class DatosRutaInvalidosException extends Exception{
	
	public DatosRutaInvalidosException(String detalles) {
		super("Se han ingresado uno o m�s datos inv�lidos. \n"
			+ "Por favor, revise y vuelva a intentarlo. \n\n" + detalles);
	}
}
