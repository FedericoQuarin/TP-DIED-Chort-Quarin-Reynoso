package excepciones;

public class InputInvalidaException extends Exception {
	
	public InputInvalidaException() {
		super("Se han ingresado uno o m�s datos inv�lidos. Por favor,\n"+
			  "revise y vuelva a intentarlo. \nRecuerde que: \n\n"+
			  "- El nombre puede tener como m�ximo 30 caracteres de longitud. \n"+
			  "- La hora debe encontrarse en el intervalo [0, 23]. \n"+
			  "- Los minutos deben encontrarse en el intervalo [0,59].\n"+
			  "- La hora de cierre debe ser mayor a la hora de inicio.\n");
	}

}
