package com.winkball.whiteboard.webservice.xmlrpc.calls.trac;

import com.winkball.whiteboard.domain.Ticket;
import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.trac.TicketArrayTransformer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.trac.TicketTransformer;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Represents a call to retrieve multiple tickets via an RPC Multicall.
 * <p>
 * An RPC Multicall executes <code>n</code> number of RPC calls within the scope of a single request. This reduces the
 * number of round-trips to remote servers in order to perform sequential operations. An aggregate response containing
 * the results of each RPC call is returned to the client.
 * </p>
 */
public class GetTickets implements RemoteProcedureCall<Ticket> {

    private Object[] parameters; //Map of the form - {'methodName': string, 'params': array}

    public GetTickets(List<Integer> ticketIds) {
        Object[] parms = initParams(ticketIds);
        this.parameters = new Object[1];
        parameters[0] = parms;
    }

    public String getMethodName() {
        // Uses the Trac system multicall which executes a sequence of RPC methods within the scope of a single request
        return "system.multicall";
    }

    public Object[] getParameters() {
        return parameters;
    }

    public ResultTransformer<Ticket> getResultTransformer() {
        return new TicketArrayTransformer();
    }

    private Map<String, Object>[] initParams(List<Integer> ids) {
        Map<String, Object>[] calls = new Map[ids.size()];
        for (int i = 0; i < calls.length; i++) {
            calls[i] = createMultiCall("ticket.get", ids.get(i));
        }

        return calls;
    }

    private Map<String, Object> createMultiCall(String methodName, Object... parameters) {
        Map<String, Object> table = new Hashtable<String, Object> ();
        table.put("methodName", methodName);
        table.put("params", parameters);
        return table;
    }
}
