package cn.loylin.lochat.xmpp.net;

public interface ConnectionCloseListener {

    /**
     * Invoked when a connection is closed.
     *
     * @param handback The handback object
     */
    public void onConnectionClose(Object handback);
}
