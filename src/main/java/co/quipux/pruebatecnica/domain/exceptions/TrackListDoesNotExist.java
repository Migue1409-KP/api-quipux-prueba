package co.quipux.pruebatecnica.domain.exceptions;

public class TrackListDoesNotExist extends RuntimeException {
    public TrackListDoesNotExist(String message) {
        super(message);
    }
}
