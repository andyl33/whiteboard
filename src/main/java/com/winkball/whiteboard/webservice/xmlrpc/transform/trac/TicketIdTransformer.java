package io.andylee.whiteboard.webservice.xmlrpc.transform.trac;

import io.andylee.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

/**
 * Transforms an Object returned by an XML remote procedure call into a Integer which represents a Trac ticket Id.
 *
 * @see {@link org.apache.xmlrpc.client.XmlRpcClient}
 * @see {@link io.andylee.whiteboard.webservice.xmlrpc.XmlRpcTemplate}
 */
public class TicketIdTransformer implements ResultTransformer<Integer> {

    public Integer transform(Object obj) {
        return obj instanceof Integer ? (Integer)obj : null;
    }
}
