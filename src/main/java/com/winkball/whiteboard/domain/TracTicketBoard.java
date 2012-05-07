package com.winkball.whiteboard.domain;

import com.winkball.whiteboard.data.TicketRepository;
import com.winkball.whiteboard.domain.configuration.BoardConfiguration;
import com.winkball.whiteboard.domain.configuration.ColumnConfiguration;
import com.winkball.whiteboard.domain.configuration.TicketConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 *
 */
public class TracTicketBoard implements TicketBoard {

    private Map<String, Column> columns;

    private BoardConfiguration boardConfiguration;

    @Autowired
    private TicketRepository ticketRepository;

    public TracTicketBoard(BoardConfiguration boardConfiguration, TicketRepository ticketRepository) {
        this.boardConfiguration = boardConfiguration;
        this.ticketRepository = ticketRepository;
    }

    public Map<String, Column> getColumns() {
        return columns;
    }

    public TicketConfiguration getTicketConfiguration() {
        return boardConfiguration.getTicketConfiguration();
    }

    public void apply(Ticket ticket, Transition transition) throws UnknownColumnException, InvalidStateException {
        // Apply transition change between columns
        // - find destination column
        // - call column.apply
        // - find origin column
        // - call column.remove
    }

    public String getTitle() {
        return boardConfiguration.getTitle();
    }

    public void initialise(Milestone milestone) {
        generateColumns(milestone);
    }

    public void generateColumns(Milestone milestone) {

        // Get all the tickets assigned to the milestone
        List<Ticket> allTickets = ticketRepository.find(milestone);
        columns = new LinkedHashMap<String, Column>();

        // Now build the columns according to the config and assign the tickets to the appropriate column
        for (ColumnConfiguration columnConfig : boardConfiguration.getColumnConfigurations()) {
            Column column = new Column(columnConfig);
            column.select(allTickets); // grab the tickets this column displays.
            columns.put(column.getTitle(), column);
        }

    }
}
