package com.winkball.whiteboard.domain.configuration;

import com.winkball.whiteboard.domain.Action;
import com.winkball.whiteboard.domain.State;
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
