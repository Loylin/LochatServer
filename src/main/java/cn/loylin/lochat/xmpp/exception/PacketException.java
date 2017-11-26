package cn.loylin.lochat.xmpp.exception;

public class PacketException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public PacketException() {
        super();
    }

    public PacketException(String message) {
        super(message);
    }

    public PacketException(Throwable cause) {
        super(cause);
    }

    public PacketException(String message, Throwable cause) {
        super(message, cause);
    }
}
