package co.quipux.pruebatecnica.domain.exceptions;

public class UserDoesNotExist extends RuntimeException {
    public UserDoesNotExist(String message) {
        super(message);
    }
}
