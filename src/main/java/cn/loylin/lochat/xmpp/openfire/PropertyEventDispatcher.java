package cn.loylin.lochat.xmpp.openfire;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PropertyEventDispatcher {

    private static Set<PropertyEventListener> listeners =
            new CopyOnWriteArraySet<PropertyEventListener>();

    private PropertyEventDispatcher() {
        // Not instantiable.
    }

    /**
     * Registers a listener to receive events.
     *
     * @param listener the listener.
     */
    public static void addListener(PropertyEventListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        listeners.add(listener);
    }

    /**
     * Unregisters a listener to receive events.
     *
     * @param listener the listener.
     */
    public static void removeListener(PropertyEventListener listener) {
        listeners.remove(listener);
    }

    /**
     * Dispatches an event to all listeners.
     *
     * @param property the property.
     * @param eventType the event type.
     * @param params event parameters.
     */
    public static void dispatchEvent(String property, EventType eventType, Map<String, Object> params) {
        for (PropertyEventListener listener : listeners) {
            try {
                switch (eventType) {
                    case property_set: {
                        listener.propertySet(property, params);
                        break;
                    }
                    case property_deleted: {
                        listener.propertyDeleted(property, params);
                        break;
                    }
                    case xml_property_set: {
                        listener.xmlPropertySet(property, params);
                        break;
                    }
                    case xml_property_deleted: {
                        listener.xmlPropertyDeleted(property, params);
                        break;
                    }
                    default:
                        break;
                }
            }
            catch (Exception e) {
                // Log.error(e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Represents valid event types.
     */
    public enum EventType {

        /**
         * A property was set.
         */
        property_set,

        /**
         * A property was deleted.
         */
        property_deleted,

        /**
         * An XML property was set.
         */
        xml_property_set,

        /**
         * An XML property was deleted.
         */
        xml_property_deleted;
    }
}
