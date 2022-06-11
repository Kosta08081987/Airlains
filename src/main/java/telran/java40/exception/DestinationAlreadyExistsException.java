package telran.java40.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
public class DestinationAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6425027450186059315L;

	public DestinationAlreadyExistsException(String message) {
		super("Destination " + message + "already exists");
	}

	
	
}
