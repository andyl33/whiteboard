package com.winkball.whiteboard.domain.predicate;

import org.apache.commons.collections.Predicate;

/**
 *
 */
public interface PredicateFactory {

    Predicate getPredicate(String pattern);
}
