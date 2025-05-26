package io.andylee.whiteboard.domain.predicate;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Builds predicates based on a String pattern
 */
public class TracPredicateFactory implements PredicateFactory {

    private static final String AND_CONDITION = "&&";

    private static final String OR_CONDITION = "||";

    private static final String OWNER_CONDITION = "owner";

    private static final String STATUS_CONDITION = "status";

    private static final String VALUE_SEPARATOR = "=";

    public Predicate getPredicate(String pattern) {
        // "status=new&&user=backlog" or "status=assigned" or "status=failed_testing||user=testing"
        Predicate _ticketFilter = null;
        if (pattern != null) {
            if (pattern.contains(AND_CONDITION)) {
                _ticketFilter = generateAndCondition(pattern);
            } else if (pattern.contains(OR_CONDITION)) {
                _ticketFilter = generateAndCondition(pattern);
            } else if (pattern.startsWith(OWNER_CONDITION)) {
                _ticketFilter = new TicketOwnerPredicate(StringUtils.substringAfter(pattern, VALUE_SEPARATOR));
            } else if (pattern.startsWith(STATUS_CONDITION)) {
                _ticketFilter = new TicketStatusPredicate(StringUtils.substringAfter(pattern, VALUE_SEPARATOR));
            }
        }
        return _ticketFilter;
    }

    private Predicate generateAndCondition(String pattern) {
        String [] values = StringUtils.splitByWholeSeparator(pattern, AND_CONDITION);
        Predicate predicate1 = null;
        Predicate predicate2 = null;
        if (ArrayUtils.isNotEmpty(values)) {
            for (String condition : values) {
                if (condition.startsWith(OWNER_CONDITION)) {
                    predicate1 = new TicketOwnerPredicate(StringUtils.substringAfter(condition, VALUE_SEPARATOR));
                }
                if (condition.startsWith(STATUS_CONDITION)) {
                    predicate2 = new TicketStatusPredicate(StringUtils.substringAfter(condition, VALUE_SEPARATOR));
                }
            }
        }
        return PredicateUtils.andPredicate(predicate1, predicate2);
    }

    private Predicate generateOrCondition(String pattern) {
        String [] values = StringUtils.splitByWholeSeparator(pattern, OR_CONDITION);
        Predicate predicate1 = null;
        Predicate predicate2 = null;
        if (ArrayUtils.isNotEmpty(values)) {
            for (String condition : values) {
                if (condition.startsWith(OWNER_CONDITION)) {
                    predicate1 = new TicketOwnerPredicate(StringUtils.substringAfter(condition, VALUE_SEPARATOR));
                }
                if (condition.startsWith(STATUS_CONDITION)) {
                    predicate2 = new TicketStatusPredicate(StringUtils.substringAfter(condition, VALUE_SEPARATOR));
                }
            }
        }
        return PredicateUtils.orPredicate(predicate1, predicate2);
    }

}
