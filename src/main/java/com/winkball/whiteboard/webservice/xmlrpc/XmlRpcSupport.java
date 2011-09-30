package com.winkball.whiteboard.webservice.xmlrpc;

import com.winkball.whiteboard.webservice.xmlrpc.calls.RemoteProcedureCall;

import java.util.List;

/**
 * Defines operations to support the execution and transformation of XML remote procedure calls.
 */
public interface XmlRpcSupport {

    <T> List<T> callForList(RemoteProcedureCall<T> remoteProcedureCall) throws RemoteProcedureCallException;

    <T> T callForObject(RemoteProcedureCall<T> remoteProcedureCall) throws RemoteProcedureCallException;
}
