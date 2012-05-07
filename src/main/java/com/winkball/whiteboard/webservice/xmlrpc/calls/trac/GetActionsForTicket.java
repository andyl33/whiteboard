package com.winkball.whiteboard.webservice.xmlrpc.calls.trac;

import com.winkball.whiteboard.domain.Action;
import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.trac.ActionTransformer;

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
