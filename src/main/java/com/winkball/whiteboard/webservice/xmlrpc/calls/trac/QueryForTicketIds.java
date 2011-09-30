package com.winkball.whiteboard.webservice.xmlrpc.calls.trac;

import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.trac.TicketIdTransformer;

/**
 *
 */
public class QueryForTicketIds implements RemoteProcedureCall<Integer> {

    private Object[] parameters;

    public QueryForTicketIds(String query) {
        this.parameters = new Object[] {query};
    }

    public String getMethodName() {
        return "ticket.query";
    }

    public Object[] getParameters() {
        return parameters;
    }

    public ResultTransformer<Integer> getResultTransformer() {
        return new TicketIdTransformer();
    }
}
