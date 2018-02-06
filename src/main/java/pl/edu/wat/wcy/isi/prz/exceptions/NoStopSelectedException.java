package pl.edu.wat.wcy.isi.prz.exceptions;

public class NoStopSelectedException extends Exception {

    public final String type;

    public NoStopSelectedException(String type) {
        this.type = type;
    }
}
