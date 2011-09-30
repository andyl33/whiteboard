package com.winkball.whiteboard.webservice.xmlrpc.calls.trac;

import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.transform.trac.MilestoneTransformer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

/**
 *
 */
public class GetAllMilestones implements RemoteProcedureCall<Milestone> {

    public String getMethodName() {
        return "ticket.milestone.getAll";
    }

    public Object[] getParameters() {
        return null;
    }

    public ResultTransformer<Milestone> getResultTransformer() {
        return new MilestoneTransformer();
    }
}
