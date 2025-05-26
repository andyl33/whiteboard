package io.andylee.whiteboard.webservice.xmlrpc.calls.trac;

import io.andylee.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import io.andylee.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import io.andylee.whiteboard.webservice.xmlrpc.transform.trac.TicketTransformer;
import io.andylee.whiteboard.domain.Ticket;

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
