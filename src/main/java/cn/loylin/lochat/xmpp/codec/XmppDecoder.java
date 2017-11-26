package cn.loylin.lochat.xmpp.codec;

import cn.loylin.lochat.xmpp.XmppIoHandler;
import cn.loylin.lochat.xmpp.openfire.XMLLightweightParser;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class XmppDecoder extends CumulativeProtocolDecoder {


    @Override
    public boolean doDecode(IoSession session, IoBuffer in,
                            ProtocolDecoderOutput out) throws Exception {
        // log.debug("doDecode(...)...");

        XMLLightweightParser parser = (XMLLightweightParser) session
                .getAttribute(XmppIoHandler.XML_PARSER);
        parser.read(in);

        if (parser.areThereMsgs()) {
            for (String stanza : parser.getMsgs()) {
                out.write(stanza);
            }
        }
        return !in.hasRemaining();
    }

}
