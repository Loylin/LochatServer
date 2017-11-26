package cn.loylin.lochat.xmpp.ssl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class SSLTrustManagerFactory {

    private static final Log log = LogFactory.getLog(SSLTrustManagerFactory.class);

    public static TrustManager[] getTrustManagers(String storeType,
                                                  String truststore, String trustpass)
            throws NoSuchAlgorithmException, KeyStoreException, IOException,
            CertificateException {
        TrustManager[] trustManagers;
        if (truststore == null) {
            trustManagers = null;
        } else {
            TrustManagerFactory trustFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            if (trustpass == null) {
                trustpass = "";
            }
            KeyStore keyStore = KeyStore.getInstance(storeType);
            keyStore.load(new FileInputStream(truststore), trustpass
                    .toCharArray());
            trustFactory.init(keyStore);
            trustManagers = trustFactory.getTrustManagers();
        }
        return trustManagers;
    }

    public static TrustManager[] getTrustManagers(KeyStore truststore,
                                                  String trustpass) {
        TrustManager[] trustManagers;
        try {
            if (truststore == null) {
                trustManagers = null;
            } else {
                TrustManagerFactory trustFactory = TrustManagerFactory
                        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
                if (trustpass == null) {
                    trustpass = SSLConfig.getc2sTrustPassword();
                }

                trustFactory.init(truststore);

                trustManagers = trustFactory.getTrustManagers();
            }
        } catch (KeyStoreException e) {
            trustManagers = null;
            log.error("SSLTrustManagerFactory startup problem.", e);
        } catch (NoSuchAlgorithmException e) {
            trustManagers = null;
            log.error("SSLTrustManagerFactory startup problem.", e);
        }
        return trustManagers;
    }
}
