package com.winkball.whiteboard.webservice.xmlrpc.calls;

import com.winkball.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

/**
 * Represents an XML Remote Procedure Call.
 */
public interface RemoteProcedureCall<T> {

    String getMethodName();

    Object[] getParameters();

    ResultTransformer<T> getResultTransformer();
}
