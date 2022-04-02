package exceptions;

public class UsuarioInexistenteException extends Exception {
    public UsuarioInexistenteException() {
        super("UsuarioInexistenteException: El usuario proporcionado no existe o su archivo de usuarios se encuentra corrupto");
    }

    public UsuarioInexistenteException(String msg) {
        super(msg);
    }
}