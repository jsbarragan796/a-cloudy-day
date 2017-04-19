package co.edu.uniandes.rest.corazon.exceptions;

/**
 * Representa las excepciones de la lógica de CityLogic 
 */
public class EmergenciaLogicException extends Exception {

	/**
	 * versión usada en la serialización de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public EmergenciaLogicException() {
	}

	/**
	 * Constructor con un mensaje
	 * @param message mensaje de la excepción
	 */
	public EmergenciaLogicException(String message) {
		super(message);
	}

	/**
	 * Constructor con una causa
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public EmergenciaLogicException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con mensaje y causa.
	 * @param message mensaje de la excepción
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public EmergenciaLogicException(String message, Throwable cause) {
		super(message, cause);
	}

}
