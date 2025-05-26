package io.andylee.whiteboard.webservice.xmlrpc.transform.trac;

import io.andylee.whiteboard.domain.Action;
import io.andylee.whiteboard.domain.Ticket;
import io.andylee.whiteboard.webservice.xmlrpc.transform.ResultTransformer;

/**
 * Transforms ticket actions
 */
public class ActionTransformer implements ResultTransformer<Action> {

    public Action transform(Object obj) {
        Action action = null;
        if (obj != null && obj.getClass().isArray() ) {
            Object[] array = (Object[])obj;
            String actionName = (String) array[0];
            String actionLabel = (String) array[1];
            String hint = (String) array[2];
            Object[] inputFields = (Object[]) array[3];
            action = new Action(actionName, actionLabel, hint, inputFields);
        }
        return action;
    }
}
