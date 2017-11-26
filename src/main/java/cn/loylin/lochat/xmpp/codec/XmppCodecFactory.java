package cn.loylin.lochat.xmpp.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class XmppCodecFactory implements ProtocolCodecFactory{

    private final XmppEncoder encoder;

    private final XmppDecoder decoder;

    /**
     * Constructor.
     */
    public XmppCodecFactory() {
        encoder = new XmppEncoder();
        decoder = new XmppDecoder();
    }

    /**
     * Returns a new (or reusable) instance of ProtocolEncoder.
     */
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    /**
     * Returns a new (or reusable) instance of ProtocolDecoder.
     */
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
