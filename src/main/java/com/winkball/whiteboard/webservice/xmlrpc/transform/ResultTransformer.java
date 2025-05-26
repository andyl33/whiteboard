package io.andylee.whiteboard.webservice.xmlrpc.transform;

/**
 * Defines methods to support the conversion of XML-RPC results into domain objects.
 */
public interface ResultTransformer<T> {

    T transform(Object obj);
}
