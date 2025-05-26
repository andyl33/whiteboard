package io.andylee.whiteboard.domain.configuration;

import io.andylee.whiteboard.domain.Action;
import io.andylee.whiteboard.domain.State;
import org.apache.commons.collections.Predicate;

import java.util.List;

/**
 *
 */
public interface ColumnConfiguration {

    Predicate getTicketFilter();

    List<Action> getActions();

    List<State> getSupportedStates();

    Integer getMaxNumberOfTickets();

    String getTitle();
}
