package io.andylee.whiteboard.webservice.xmlrpc.security;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * An {@link X509TrustManager} which does not validate certificate chains.
 * <p>
 * This is useful for situations where we do not require SSL certificate validation
 * during an SSL handshake. In this case we just want to trust whatever certificate
 * is presented regardless of its veracity.
 * </p>
 * @author Andrew Lee
 */
public class NonValidatingTrustManager implements X509TrustManager {

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public void checkClientTrusted(X509Certificate[] certs, String authType) {
        // Trust always
    }

    public void checkServerTrusted(X509Certificate[] certs, String authType) {
        // Trust always
    }
}


