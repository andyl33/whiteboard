package io.andylee.whiteboard.domain.predicate;

import io.andylee.whiteboard.domain.Ticket;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.Validate;

/**
 * Determines whether a given Tickets status is equal to the expected value.
 */
public class TicketOwnerPredicate implements Predicate {

    private Object value;

    public TicketOwnerPredicate(Object value) {
        Validate.notNull(value, "Expected owner value cannot be NULL");
        this.value = value;
    }

    public boolean evaluate(Object o) {
        return (o instanceof Ticket) && value.equals(((Ticket)o).getOwner());
    }
}
