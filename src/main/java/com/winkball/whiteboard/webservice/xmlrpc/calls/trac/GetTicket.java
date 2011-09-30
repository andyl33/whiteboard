package com.winkball.whiteboard.webservice.xmlrpc.calls.trac;

import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.trac.TicketTransformer;
import com.winkball.whiteboard.domain.Ticket;

/**
 *
 */
public class GetTicket implements RemoteProcedureCall<Ticket> {

    private Object[] parameters;

    public GetTicket(Integer ticketId) {
        this.parameters = new Object[] {ticketId};
    }

    public String getMethodName() {
        return "ticket.get";
    }

    public Object[] getParameters() {
        return parameters;
    }

    public ResultTransformer<Ticket> getResultTransformer() {
        return new TicketTransformer();
    }
}
