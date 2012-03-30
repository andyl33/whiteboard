package com.winkball.whiteboard.webservice.xmlrpc.calls.trac;

import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

/**
 * 
 */
public class GetActionsForTicket implements RemoteProcedureCall<Object> {
    
    private Object[] params;

    public GetActionsForTicket(int id) {
        params = new Object[] {id};
    }
    
    @Override
    public String getMethodName() {
        return "ticket.getActions";
    }

    @Override
    public Object[] getParameters() {
        return params;
    }

    @Override
    public ResultTransformer<Object> getResultTransformer() {
        return null;
    }
}
