package io.andylee.whiteboard.webservice.xmlrpc.transform.trac;

import io.andylee.whiteboard.domain.Ticket;
import io.andylee.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

import java.util.Map;

/**
 *
 */
public class TicketArrayTransformer implements ResultTransformer<Ticket> {

    public Ticket transform(Object obj) {
        Ticket ticket = null;
        if (obj != null && obj.getClass().isArray() ) {
            for (Object outer : (Object[])obj) {
                if (outer != null && outer.getClass().isArray()) {
                    for (Object inner : (Object[])obj) {
                        if (inner != null && inner.getClass().isArray()) {
                            ticket = new Ticket();
                            for (Object ticketElement : (Object[])inner) {
                                if (ticketElement instanceof Integer) {
                                    ticket.setId((Integer)ticketElement);
                                }
                                if (ticketElement instanceof Map) {
                                    Map source = (Map)ticketElement;
                                    ticket.setMilestone((String)source.get("milestone"));
                                    ticket.setOwner((String)source.get("owner"));
                                    ticket.setPriority((String)source.get("priority"));
                                    ticket.setStatus((String)source.get("status"));
                                    ticket.setSummary((String)source.get("summary"));
                                    ticket.setType((String)source.get("type"));
                                }
                            }
                        }
                    }
                }
            }    
        }
        return ticket;
    }
}
