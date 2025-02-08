package co.quipux.pruebatecnica.util;

public class ObejctUtility {

    public static final String EMPTY = "";

    private ObejctUtility() {
        super();
    }

    public static final <T> boolean isNull(final T object) {
        return object == null;
    }

    public static final <T> boolean isEmpty(final T object) {
        return object.equals(EMPTY);
    }


}
