package io.andylee.whiteboard.domain;


import io.andylee.whiteboard.domain.configuration.TicketConfiguration;

import java.util.Map;
import java.util.Set;

/**
 * Represents a Process Board
 */
public interface TicketBoard {

    Map<String, Column> getColumns();

    TicketConfiguration getTicketConfiguration();

    String getTitle();

    void initialise(Milestone milestone);

    void apply(Ticket ticket, Transition transition) throws UnknownColumnException, InvalidStateException;
}
