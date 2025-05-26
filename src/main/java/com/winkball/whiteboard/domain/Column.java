package io.andylee.whiteboard.domain;

import io.andylee.whiteboard.domain.configuration.ColumnConfiguration;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class Column {

    // Filtered list of tickets this column displays according to its configuration
    private List<Ticket> tickets = new ArrayList<Ticket>();

    //
    private ColumnConfiguration configuration;

    public Column(ColumnConfiguration configuration) {
        this.configuration = configuration;
    }

    public void accept(Ticket ticket) {
        // check against valid states - throw exceptions if invalid
        // perform post processing - action changes
        // update ticket directly via repo
    }

    // Read only list of tickets in this column.
    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    /**
     * Filters tickets according to this columns configuration. This is not part of the public API for column
     * and is used for initialising the Column with the correct tickets.
     * @param source List of tickets to filter
     */
    public void select(List<Ticket> source) {
        CollectionUtils.select(source, configuration.getTicketFilter(), tickets);
    }

    public String getTitle() {
        return this.configuration.getTitle();
    }
}
