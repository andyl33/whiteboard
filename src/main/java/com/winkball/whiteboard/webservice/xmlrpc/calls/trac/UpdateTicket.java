package com.winkball.whiteboard.webservice.xmlrpc.calls.trac;

import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.trac.TicketTransformer;

import java.util.Map;

/**
 *
 */
public class UpdateTicket implements RemoteProcedureCall {

    Object[] params = new Object[6];
    
    public UpdateTicket(int id, String comment, Map<String, Object> attributes, String author) {
        //ticket.update(int id, string comment, struct attributes={}, boolean notify=False, string author="", dateTime.iso8601 when=None)
        params[0] = id;
        params[1] = comment;
        params[2] = attributes;
        params[3] = false;
        params[4] = author;
        params[5] = null;
    }

    @Override
    public String getMethodName() {
        return "ticket.update";
    }

    @Override
    public Object[] getParameters() {
        return new Object[0];
    }

    @Override
    public ResultTransformer getResultTransformer() {
        return new TicketTransformer();
    }
}
