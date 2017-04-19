package co.edu.uniandes.rest.corazon.exceptions;

/**
 * Representa las excepciones de la lógica de CityLogic 
 */
public class MedicionLogicException extends Exception {

	/**
	 * versión usada en la serialización de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public MedicionLogicException() {
	}

	/**
	 * Constructor con un mensaje
	 * @param message mensaje de la excepción
	 */
	public MedicionLogicException(String message) {
		super(message);
	}

	/**
	 * Constructor con una causa
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public MedicionLogicException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con mensaje y causa.
	 * @param message mensaje de la excepción
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public MedicionLogicException(String message, Throwable cause) {
		super(message, cause);
	}

}
