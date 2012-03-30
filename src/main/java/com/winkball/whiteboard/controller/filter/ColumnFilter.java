package com.winkball.whiteboard.controller.filter;

import com.winkball.whiteboard.domain.Ticket;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
@Component
public class ColumnFilter {

    public Map<String, List<Ticket>> filter(List<Ticket> source) {
        Map<String, List<Ticket>> filtered = new TreeMap<String, List<Ticket>>();

        for (String key : ColumnConfig.getColumnConfig().keySet()) {
            List<Ticket> tickets = new ArrayList<Ticket>();
                for (Predicate filterRule : ColumnConfig.getColumnConfig().get(key)) {
                    CollectionUtils.select(source, filterRule, tickets);    
                }
            filtered.put(key, tickets);
        }
        return filtered;
    }

}
