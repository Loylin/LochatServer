package cn.loylin.lochat.xmpp;

import cn.loylin.lochat.xmpp.session.ClientSession;
import cn.loylin.lochat.xmpp.session.SessionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;

import java.util.Random;

public class NotificationManager {

    private static final String NOTIFICATION_NAMESPACE = "androidpn:iq:notification";

    private final Log log = LogFactory.getLog(getClass());

    private SessionManager sessionManager;

    /**
     * Constructor.
     */
    public NotificationManager() {
        sessionManager = SessionManager.getInstance();
    }

    /**
     * Broadcasts a newly created notification message to all connected users.
     *
     * @param apiKey the API key
     * @param title the title
     * @param message the message details
     * @param uri the uri
     */
    public void sendBroadcast(String apiKey, String title, String message,
                              String uri) {
        log.debug("sendBroadcast()...");
        IQ notificationIQ = createNotificationIQ(apiKey, title, message, uri);
        for (ClientSession session : sessionManager.getSessions()) {
            if (session.getPresence().isAvailable()) {
                notificationIQ.setTo(session.getAddress());
                session.deliver(notificationIQ);
            }
        }
    }

    /**
     * Sends a newly created notification message to the specific user.
     *
     * @param apiKey the API key
     * @param title the title
     * @param message the message details
     * @param uri the uri
     */
    public void sendNotifcationToUser(String apiKey, String username,
                                      String title, String message, String uri) {
        log.debug("sendNotifcationToUser()...");
        IQ notificationIQ = createNotificationIQ(apiKey, title, message, uri);
        ClientSession session = sessionManager.getSession(username);
        if (session != null) {
            if (session.getPresence().isAvailable()) {
                notificationIQ.setTo(session.getAddress());
                session.deliver(notificationIQ);
            }
        }
    }

    /**
     * Creates a new notification IQ and returns it.
     */
    private IQ createNotificationIQ(String apiKey, String title,
                                    String message, String uri) {
        Random random = new Random();
        String id = Integer.toHexString(random.nextInt());
        // String id = String.valueOf(System.currentTimeMillis());

        Element notification = DocumentHelper.createElement(QName.get(
                "notification", NOTIFICATION_NAMESPACE));
        notification.addElement("id").setText(id);
        notification.addElement("apiKey").setText(apiKey);
        notification.addElement("title").setText(title);
        notification.addElement("message").setText(message);
        notification.addElement("uri").setText(uri);

        IQ iq = new IQ();
        iq.setType(IQ.Type.set);
        iq.setChildElement(notification);

        return iq;
    }
}
