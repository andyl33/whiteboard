package io.andylee.whiteboard.webservice.xmlrpc.calls.trac;

import io.andylee.whiteboard.domain.Action;
import io.andylee.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import io.andylee.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import io.andylee.whiteboard.webservice.xmlrpc.transform.trac.ActionTransformer;

/**
 * 
 */
public class GetActionsForTicket implements RemoteProcedureCall<Action> {
    
    private Object[] params;

    public GetActionsForTicket(int id) {
        params = new Object[] {id};
    }
    

    public String getMethodName() {
        return "ticket.getActions";
    }

    public Object[] getParameters() {
        return params;
    }

    public ResultTransformer<Action> getResultTransformer() {
        return new ActionTransformer();
    }
}
