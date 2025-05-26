package io.andylee.whiteboard.webservice.xmlrpc.calls.trac;

import io.andylee.whiteboard.domain.Milestone;
import io.andylee.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import io.andylee.whiteboard.webservice.xmlrpc.transform.trac.MilestoneTransformer;
import io.andylee.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

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
