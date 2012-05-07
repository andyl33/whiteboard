package com.winkball.whiteboard.domain.configuration;

import java.util.List;

/**
 *
 */
public interface BoardConfiguration {

    String getTitle();

    List<ColumnConfiguration> getColumnConfigurations();

    void add(ColumnConfiguration columnConfiguration);

    TicketConfiguration getTicketConfiguration();
}
