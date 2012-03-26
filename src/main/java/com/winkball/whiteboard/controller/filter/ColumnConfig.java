package com.winkball.whiteboard.controller.filter;

import com.winkball.whiteboard.controller.filter.predicate.TicketOwnerPredicate;
import com.winkball.whiteboard.controller.filter.predicate.TicketStatusPredicate;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 */
public class ColumnConfig {
    
    public static final String TODO_COLUMN_KEY = "todo";
    public static final String IN_PROGRESS_COLUMN_KEY = "inprogress";
    public static final String REVIEWING_COLUMN_KEY = "review";
    public static final String TESTING_COLUMN_KEY = "testing";
    public static final String DONE_COLUMN_KEY = "done";
    
    private static final TicketOwnerPredicate OWNER_IS_BACKLOG = new TicketOwnerPredicate("backlog");
    
    private static final TicketStatusPredicate STATUS_IS_NEW = new TicketStatusPredicate("new");

    private static final TicketStatusPredicate STATUS_IS_FAILED_TESTING = new TicketStatusPredicate("failed_testing");

    private static final TicketStatusPredicate STATUS_IS_ASSIGNED = new TicketStatusPredicate("assigned");

    private static final TicketStatusPredicate STATUS_IS_REVIEWING = new TicketStatusPredicate("reviewing");

    private static final TicketStatusPredicate STATUS_IS_TESTING = new TicketStatusPredicate("testing");

    private static final TicketStatusPredicate STATUS_IS_CLOSED = new TicketStatusPredicate("closed");
    
    final static Map<String, Predicate[]> columnConfig = new TreeMap<String, Predicate[]>();

    static {
        Predicate backlogNew = PredicateUtils.andPredicate(OWNER_IS_BACKLOG, STATUS_IS_NEW);
        Predicate backlogFailedTesting = PredicateUtils.andPredicate(OWNER_IS_BACKLOG, STATUS_IS_FAILED_TESTING);
        columnConfig.put(TODO_COLUMN_KEY, new Predicate[] {backlogNew, backlogFailedTesting});
        
        columnConfig.put(IN_PROGRESS_COLUMN_KEY, new Predicate[] {STATUS_IS_ASSIGNED});
        columnConfig.put(REVIEWING_COLUMN_KEY, new Predicate[] {STATUS_IS_REVIEWING});
        columnConfig.put(TESTING_COLUMN_KEY, new Predicate[] {STATUS_IS_TESTING});
        columnConfig.put(DONE_COLUMN_KEY, new Predicate[] {STATUS_IS_CLOSED});
    }
    
}
