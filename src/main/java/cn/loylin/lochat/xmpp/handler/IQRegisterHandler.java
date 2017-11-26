package cn.loylin.lochat.xmpp.handler;

import cn.loylin.lochat.xmpp.exception.UserExistsException;
import cn.loylin.lochat.xmpp.exception.UserNotFoundException;
import cn.loylin.lochat.xmpp.exception.UnauthorizedException;
import cn.loylin.lochat.xmpp.session.ClientSession;
import cn.loylin.lochat.xmpp.session.Session;
import gnu.inet.encoding.Stringprep;
import gnu.inet.encoding.StringprepException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;
import org.xmpp.packet.PacketError;

public class IQRegisterHandler extends IQHandler {

    private static final String NAMESPACE = "jabber:iq:register";

    static String psw = null;

//    private UserService userService;

    private Element probeResponse;

    /**
     * Constructor.
     */
    public IQRegisterHandler() {
//        userService = ServiceLocator.getUserService();
        probeResponse = DocumentHelper.createElement(QName.get("query",
                NAMESPACE));
        probeResponse.addElement("username");
        probeResponse.addElement("password");
        probeResponse.addElement("email");
        probeResponse.addElement("name");
    }

    /**
     * Handles the received IQ packet.
     *
     * @param packet the packet
     * @return the response to send back
     * @throws UnauthorizedException if the user is not authorized
     */
    public IQ handleIQ(IQ packet) throws UnauthorizedException {
        IQ reply = null;

        ClientSession session = sessionManager.getSession(packet.getFrom());
        if (session == null) {
            log.error("Session not found for key " + packet.getFrom());
            reply = IQ.createResultIQ(packet);
            reply.setChildElement(packet.getChildElement().createCopy());
            reply.setError(PacketError.Condition.internal_server_error);
            return reply;
        }

        if (IQ.Type.get.equals(packet.getType())) {
            reply = IQ.createResultIQ(packet);
            if (session.getStatus() == Session.STATUS_AUTHENTICATED) {
                // TODO
            } else {
                reply.setTo((JID) null);
                reply.setChildElement(probeResponse.createCopy());
            }
        } else if (IQ.Type.set.equals(packet.getType())) {
            try {
                Element query = packet.getChildElement();
                if (query.element("remove") != null) {
                    if (session.getStatus() == Session.STATUS_AUTHENTICATED) {
                        // TODO
                    } else {
                        throw new UnauthorizedException();
                    }
                } else {
                    String username = query.elementText("username");
                    String password = query.elementText("password");
                    String email = query.elementText("email");
                    String name = query.elementText("name");

                    // Verify the username
                    if (username != null) {
                        Stringprep.nodeprep(username);
                    }

                    // Deny registration of users with no password
                    if (password == null || password.trim().length() == 0) {
                        reply = IQ.createResultIQ(packet);
                        reply.setChildElement(packet.getChildElement()
                                .createCopy());
                        reply.setError(PacketError.Condition.not_acceptable);
                        return reply;
                    }

                    if (email != null && email.matches("\\s*")) {
                        email = null;
                    }

                    if (name != null && name.matches("\\s*")) {
                        name = null;
                    }

//                    User user;
//                    if (session.getStatus() == Session.STATUS_AUTHENTICATED) {
//                        user = userService.getUser(session.getUsername());
//                    } else {
//                        user = new User();
//                    }
//                    user.setUsername(username);
//                    user.setPassword(password);
//                    user.setEmail(email);
//                    user.setName(name);
//                    userService.saveUser(user);
                    psw = password;
                    reply = IQ.createResultIQ(packet);
                }
            } catch (Exception ex) {
                log.error(ex);
                reply = IQ.createResultIQ(packet);
                reply.setChildElement(packet.getChildElement().createCopy());
                if (ex instanceof UserExistsException) {
                    reply.setError(PacketError.Condition.conflict);
                } else if (ex instanceof UserNotFoundException) {
                    reply.setError(PacketError.Condition.bad_request);
                } else if (ex instanceof StringprepException) {
                    reply.setError(PacketError.Condition.jid_malformed);
                } else if (ex instanceof IllegalArgumentException) {
                    reply.setError(PacketError.Condition.not_acceptable);
                } else {
                    reply.setError(PacketError.Condition.internal_server_error);
                }
            }
        }

        // Send the response directly to the session
        if (reply != null) {
            session.process(reply);
        }
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

    public static String getPsw(){
        return psw;
    }
}
