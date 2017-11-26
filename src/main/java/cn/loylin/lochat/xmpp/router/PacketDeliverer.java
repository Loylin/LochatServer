package cn.loylin.lochat.xmpp.router;

import cn.loylin.lochat.xmpp.exception.PacketException;
import cn.loylin.lochat.xmpp.session.ClientSession;
import cn.loylin.lochat.xmpp.session.SessionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmpp.packet.JID;
import org.xmpp.packet.Packet;

public class PacketDeliverer {

    private static final Log log = LogFactory.getLog(PacketDeliverer.class);

    /**
     * Delivers the packet to the packet recipient.
     *
     * @param packet the packet to deliver
     * @throws PacketException if the packet is null or the recipient was not found.
     */
    public static void deliver(Packet packet) throws PacketException {
        if (packet == null) {
            throw new PacketException("Packet was null");
        }

        try {
            JID recipient = packet.getTo();
            if (recipient != null) {
                ClientSession clientSession = SessionManager.getInstance()
                        .getSession(recipient);
                if (clientSession != null) {
                    clientSession.deliver(packet);
                }
            }
        } catch (Exception e) {
            log.error("Could not deliver packet: " + packet.toString(), e);
        }
    }
}
