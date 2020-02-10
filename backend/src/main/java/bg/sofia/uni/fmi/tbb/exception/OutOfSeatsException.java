package bg.sofia.uni.fmi.tbb.exception;

public class OutOfSeatsException extends Exception {
    public OutOfSeatsException() {
    }

    public OutOfSeatsException(String message) {
        super(message);
    }

    public OutOfSeatsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfSeatsException(Throwable cause) {
        super(cause);
    }
}

