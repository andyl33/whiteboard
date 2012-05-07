package com.winkball.whiteboard.domain.configuration;

import com.winkball.whiteboard.domain.Action;
import com.winkball.whiteboard.domain.State;
import com.winkball.whiteboard.domain.predicate.TicketOwnerPredicate;
import com.winkball.whiteboard.domain.predicate.TicketStatusPredicate;
import com.winkball.whiteboard.domain.predicate.TracPredicateFactory;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 *
 **/
public final class TracColumnConfiguration implements ColumnConfiguration {

    public TracColumnConfiguration() {}

    private String title; // user friendly column title

    private Integer max; // maximum number of tickets the column can contain

    private List<String> acceptedStates; // tickets with this status can be moved to this column

    private String after; // action to perform after a ticket is moved to this column  // TODO generate post transition Actions from String

    private Predicate ticketFilter;

    public TracColumnConfiguration(String title) {
        this.title = title;
    }

    public void acceptsTicketsWith(String acceptConditions) {

    }

    public void containsTicketsWith(String containsConditions) {
        this.ticketFilter = new TracPredicateFactory().getPredicate(containsConditions);
    }

    public void onAccept(String transformConditions) {

    }

    public void setMaxTickets(Integer max) {
        this.max = max;
    }

    public String getTitle() {
        return title;
    }

    public Predicate getTicketFilter() {
        return ticketFilter;
    }

    public List<Action> getActions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<State> getSupportedStates() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getMaxNumberOfTickets() {
        return max;
    }
}
