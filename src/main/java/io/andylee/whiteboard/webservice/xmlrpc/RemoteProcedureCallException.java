package io.andylee.whiteboard.webservice.xmlrpc;

/**
 *  Runtime equivalent of the {@link org.apache.xmlrpc.XmlRpcException}
 */
public class RemoteProcedureCallException extends RuntimeException {

    public RemoteProcedureCallException(String s) {
        super(s);
    }

    public RemoteProcedureCallException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
