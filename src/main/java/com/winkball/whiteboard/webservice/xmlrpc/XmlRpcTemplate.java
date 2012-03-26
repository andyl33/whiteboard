package com.winkball.whiteboard.webservice.xmlrpc;

import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;
import com.winkball.whiteboard.webservice.xmlrpc.security.SslInitializer;
import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;
import org.apache.commons.lang.Validate;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides common XML-RPC operations which automatically apply transforms from the remote procedure call results to
 * domain objects.
 */
@Component
public class XmlRpcTemplate implements XmlRpcSupport {

    private static final Logger LOG = LoggerFactory.getLogger(XmlRpcTemplate.class);

    private XmlRpcClient client;

    @Autowired
    public XmlRpcTemplate(XmlRpcClient client) {
        Validate.notNull(client, "XmlRpcClient cannot be null");
        this.client = client;
        SslInitializer sslInitializer = new SslInitializer();
        sslInitializer.init();
    }

    public <T> List<T> callForList(RemoteProcedureCall<T> remoteProcedureCall) throws RemoteProcedureCallException {

        Validate.notNull(remoteProcedureCall, "Remote procedure call cannot be null.");
        Validate.notNull(remoteProcedureCall.getMethodName(), "Remote procedure call method name cannot be null.");
        Validate.notNull(remoteProcedureCall.getResultTransformer(), "ResultTransformer cannot be null.");

        Object[] result;
        try {
            result = (Object[]) client.execute(remoteProcedureCall.getMethodName(), remoteProcedureCall.getParameters());
        } catch (XmlRpcException e) {
            LOG.error("Failed to invoke XML-RPC call: {}", remoteProcedureCall.getMethodName());
            throw new RemoteProcedureCallException("Failed to invoke XML-RPC call", e);
        }

        if (result == null || result.length == 0) {
            throw new RemoteProcedureCallException("No results were returned from the call: " + remoteProcedureCall.getMethodName());
        }

        return transform(remoteProcedureCall.getResultTransformer(), result);
    }

    public <T> T callForObject(RemoteProcedureCall<T> remoteProcedureCall) throws RemoteProcedureCallException {

        //TODO change as result may not return a list. Instead client.execute() and if Object[] then get [0]
        List<T> result = callForList(remoteProcedureCall);

        if (result.size() > 1) {
            throw new RemoteProcedureCallException("More than one result found for call "
                    + remoteProcedureCall.getMethodName() + " with params " +
                    Arrays.toString(remoteProcedureCall.getParameters()));
        }

        return result.get(0);
    }

    private <T> List<T> transform(ResultTransformer<T> transformer, Object[] result) {
        List<T> transformedResults = new ArrayList<T>();
        for (Object aResult : result) {
            T transformed = transformer.transform(aResult);
            if (transformed != null) {
                transformedResults.add(transformed);
            }
        }
        return transformedResults;
    }

}
