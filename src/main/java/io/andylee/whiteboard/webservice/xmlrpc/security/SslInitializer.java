package io.andylee.whiteboard.webservice.xmlrpc.security;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Initializes JVM SSL support.
 * <p>
 * Note this defaults to use a {@link NonValidatingHostnameVerifier} and {@link NonValidatingTrustManager} if none are
 * explicitly provided so that SSL certificates presented by remote hosts are never validated and are implicitly trusted.
 * </p>
 */
public class SslInitializer {

    private HostnameVerifier hostnameVerifier = new NonValidatingHostnameVerifier();

    private TrustManager[] trustManagers = new TrustManager[] {new NonValidatingTrustManager()};

    /**
     * Bootstraps the {@link javax.net.ssl.SSLContext} and {javax.net.ssl.HttpsURLConnection} classes with a custom
     * {@link HostnameVerifier} and {@link TrustManager}s.
     */
    public void init() {

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");

            if (sslContext != null) {
                sslContext.init(null, trustManagers, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
            } else {
                throw new RuntimeException("Unable to initialise SSL. The SSLContext is not available.");
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to initialise SSL. " +
                    "No supported algorithm named SSL was found.", e);
        } catch (KeyManagementException e) {
            throw new RuntimeException("Unable to initialise SSL.");
        }
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
    }

    public void setTrustManagers(TrustManager[] trustManagers) {
        this.trustManagers = trustManagers;
    }
}
