package excepciones;

public class NombreEstacionRepetidoException extends Exception {
	
	public NombreEstacionRepetidoException() {
		super("El nombre ingresado es igual al de otra estaci�n. Por favor,\n ingrese otro nombre que no est� repetido.");
	}

}
