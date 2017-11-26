package cn.loylin.lochat.xmpp;

import cn.loylin.lochat.xmpp.dom4j.XMPPPacketReader;
import cn.loylin.lochat.xmpp.net.Connection;
import cn.loylin.lochat.xmpp.net.StanzaHandler;
import cn.loylin.lochat.xmpp.openfire.MXParser;
import cn.loylin.lochat.xmpp.openfire.XMLLightweightParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XmppIoHandler implements IoHandler{

    private static final Log log = LogFactory.getLog(XmppIoHandler.class);

    public static final String XML_PARSER = "XML_PARSER";

    private static final String CONNECTION = "CONNECTION";

    private static final String STANZA_HANDLER = "STANZA_HANDLER";

    private String serverName;

    private static Map<Integer, XMPPPacketReader> parsers = new ConcurrentHashMap<Integer, XMPPPacketReader>();

    private static XmlPullParserFactory factory = null;

    static {
        try {
            factory = XmlPullParserFactory.newInstance(MXParser.class.getName(), null);
            factory.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            log.error("Error creating a parser factory", e);
        }
    }

    /**
     * Constructor. Set the server name from server instance.
     */
    protected XmppIoHandler() {
        serverName = XmppServer.getInstance().getServerName();
    }

    /**
     * Invoked from an I/O processor thread when a new connection has been created.
     */
    public void sessionCreated(IoSession session) throws Exception {
        log.debug("sessionCreated()...");
    }

    /**
     * Invoked when a connection has been opened.
     */
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("sessionOpened()...");
        log.debug("remoteAddress=" + session.getRemoteAddress());
        // Create a new XML parser
        XMLLightweightParser parser = new XMLLightweightParser("UTF-8");
        session.setAttribute(XML_PARSER, parser);
        // Create a new connection
        Connection connection = new Connection(session);
        session.setAttribute(CONNECTION, connection);
        session.setAttribute(STANZA_HANDLER, new StanzaHandler(serverName, connection));
    }

    /**
     * Invoked when a connection is closed.
     */
    public void sessionClosed(IoSession session) throws Exception {
        log.debug("sessionClosed()...");
        Connection connection = (Connection) session.getAttribute(CONNECTION);
        connection.close();
    }

    /**
     * Invoked with the related IdleStatus when a connection becomes idle.
     */
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.debug("sessionIdle()...");
        Connection connection = (Connection) session.getAttribute(CONNECTION);
        if (log.isDebugEnabled()) {
            log.debug("Closing connection that has been idle: " + connection);
        }
        connection.close();
    }

    /**
     * Invoked when any exception is thrown.
     */
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        log.debug("exceptionCaught()...");
        log.error(cause);
    }

    /**
     * Invoked when a message is received.
     */
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        log.debug("messageReceived()...");
        log.debug("RCVD: " + message);

        // Get the stanza handler
        StanzaHandler handler = (StanzaHandler) session.getAttribute(STANZA_HANDLER);

        // Get the XMPP packet parser
        int hashCode = Thread.currentThread().hashCode();
        XMPPPacketReader parser = parsers.get(hashCode);
        if (parser == null) {
            parser = new XMPPPacketReader();
            parser.setXPPFactory(factory);
            parsers.put(hashCode, parser);
        }

        // The stanza handler processes the message
        try {
            handler.process((String) message, parser);
        } catch (Exception e) {
            log.error("Closing connection due to error while processing message: " + message, e);
            Connection connection = (Connection) session.getAttribute(CONNECTION);
            connection.close();
        }
    }

    /**
     * Invoked when a message written by IoSession.write(Object) is sent out.
     */
    public void messageSent(IoSession session, Object message) throws Exception {
        log.debug("messageSent()...");
    }

    public void inputClosed(IoSession ioSession) throws Exception {

    }

}
