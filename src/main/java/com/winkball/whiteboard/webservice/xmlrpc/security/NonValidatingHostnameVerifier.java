package io.andylee.whiteboard.webservice.xmlrpc.security;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * A {@link HostnameVerifier} which does not attempt to validate the hostname of an SSL host.
 * <p>
 * This is designed to be used in tandem with the {@link NonValidatingTrustManager} to support
 * automatic trust of any SSL certificate. Note that by its very nature using this is not secure!
 * </p>
 */
public class NonValidatingHostnameVerifier implements HostnameVerifier {

    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
