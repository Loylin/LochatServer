package cn.loylin.lochat.xmpp.handler;

import cn.loylin.lochat.xmpp.exception.UnauthorizedException;
import org.xmpp.packet.IQ;

public class IQRosterHandler extends IQHandler {

    private static final String NAMESPACE = "jabber:iq:roster";

    /**
     * Constructor.
     */
    public IQRosterHandler() {
    }

    /**
     * Handles the received IQ packet.
     *
     * @param packet the packet
     * @return the response to send back
     * @throws UnauthorizedException if the user is not authorized
     */
    public IQ handleIQ(IQ packet) throws UnauthorizedException {
        // TODO
        return null;
    }

    /**
     * Returns the namespace of the handler.
     *
     * @return the namespace
     */
    public String getNamespace() {
        return NAMESPACE;
    }
}
