package cn.loylin.lochat.xmpp.router;

import org.xmpp.packet.Message;

public class MessageRouter {

    /**
     * Constucts a packet router.
     */
    public MessageRouter() {
    }

    /**
     * Routes the Message packet.
     *
     * @param packet the packet to route
     */
    public void route(Message packet) {
        throw new RuntimeException("Please implement this!");
    }
}
