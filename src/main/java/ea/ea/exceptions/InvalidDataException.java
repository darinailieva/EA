package ea.ea.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception when the submitted data has no valid format.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String type) {
        super(String.format("Invalid %s.", type));
    }
}
