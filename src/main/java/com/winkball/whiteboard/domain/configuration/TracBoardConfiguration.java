package io.andylee.whiteboard.domain.configuration;

import java.util.*;

/**
 *
 */
public class TracBoardConfiguration implements BoardConfiguration {

    private String title;

    private List<ColumnConfiguration> columnConfigurations = new ArrayList<ColumnConfiguration>();

    private TicketConfiguration ticketConfiguration;

    public TracBoardConfiguration(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<ColumnConfiguration> getColumnConfigurations() {
        return columnConfigurations;
    }

    public void add(ColumnConfiguration columnConfiguration) {
        columnConfigurations.add(columnConfiguration);
    }

    public TicketConfiguration getTicketConfiguration() {
        return ticketConfiguration;
    }

    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }
}
