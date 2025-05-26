package io.andylee.whiteboard.webservice.xmlrpc.transform.trac;

import io.andylee.whiteboard.domain.Milestone;
import io.andylee.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

/**
 * Transforms an Object returned by an XML remote procedure call into a {@link Milestone}.
 *
 * @see {@link org.apache.xmlrpc.client.XmlRpcClient}
 * @see {@link io.andylee.whiteboard.webservice.xmlrpc.XmlRpcTemplate}
 */
public class MilestoneTransformer implements ResultTransformer<Milestone> {

    public Milestone transform(Object obj) {
        Milestone milestone = null;
        if (obj instanceof String) {
            milestone = new Milestone((String)obj);
        }
        return milestone;
    }
}
