package cn.loylin.lochat.xmpp.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class XmppEncoder implements ProtocolEncoder {

    public void encode(IoSession session, Object message,
                       ProtocolEncoderOutput out) throws Exception {
        // log.debug("encode()...");
    }

    public void dispose(IoSession session) throws Exception {
        // log.debug("dispose()...");
    }
}
