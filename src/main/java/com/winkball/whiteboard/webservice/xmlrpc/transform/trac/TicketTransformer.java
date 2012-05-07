package com.winkball.whiteboard.webservice.xmlrpc.transform.trac;

import com.winkball.whiteboard.domain.Ticket;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

import java.util.Map;

/**
 * Transforms an Object returned by an XML remote procedure call into a {@link Ticket}.
 *
 * @see {@link org.apache.xmlrpc.client.XmlRpcClient}
 * @see {@link com.winkball.whiteboard.webservice.xmlrpc.XmlRpcTemplate}
 */
public class TicketTransformer implements ResultTransformer<Ticket> {

    public Ticket transform(Object obj) {
        Ticket ticket = null;
        if (obj instanceof Map)  {
            Map source = (Map)obj;
            ticket = new Ticket();
            ticket.setMilestone((String)source.get("milestone"));
            ticket.setOwner((String)source.get("owner"));
            ticket.setPriority((String)source.get("priority"));
            ticket.setSummary((String)source.get("summary"));
            ticket.setType((String)source.get("type"));
            ticket.setStatus((String)source.get("status"));
            ticket.setVersion((String)source.get("_ts"));
        }
        return ticket;
    }

}
