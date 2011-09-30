package com.winkball.whiteboard.controller;

import com.winkball.whiteboard.data.MilestoneDAO;
import com.winkball.whiteboard.data.TicketDAO;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.domain.Ticket;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Controller for generating the board contents and handling updates.
 */
@Controller
public class BoardController {

    @Autowired
    private MilestoneDAO milestoneDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/whiteboard")
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView("whiteboard");

        // TODO select milestone from dropdown on UI
        List<Ticket> allTickets = ticketDAO.find(new Milestone("WinkBall 1.8.3"));

        // TODO Refactor! Query Trac for these tickets per column? Or convert in Java?
        List<Ticket> todo = new ArrayList<Ticket>();
        todo.addAll(filterBy(allTickets, "backlog", "new"));
        todo.addAll(filterBy(allTickets, "backlog", "failed_testing"));

        mav.addObject("todo", todo);
        mav.addObject("inprogress", filterBy(allTickets, "assigned"));
        mav.addObject("review", filterBy(allTickets, "reviewing"));
        mav.addObject("testing", filterBy(allTickets, "testing"));
        mav.addObject("done", filterBy(allTickets, "closed"));
        mav.addObject("milestone", "WinkBall 1.8.3");

        return mav;
    }

    private List<Ticket> filterBy(List<Ticket> target, final String owner, final String status) {
        List<Ticket> filtered = new ArrayList<Ticket>();
        CollectionUtils.select(target, new Predicate() {
            public boolean evaluate(Object object) {
                return ((Ticket) object).getOwner().equalsIgnoreCase(owner) &&
                        ((Ticket) object).getStatus().equalsIgnoreCase(status);
            }
        }, filtered);
        return filtered;
    }

    private List<Ticket> filterBy(List target, final String status) {
        List<Ticket> filtered = new ArrayList<Ticket>();
        CollectionUtils.select(target, new Predicate() {
            public boolean evaluate(Object object) {
                return ((Ticket) object).getStatus().equalsIgnoreCase(status);
            }
        }, filtered);
        return filtered;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post() {
        // TODO Save changes back to Trac via the TicketDAO
    }

    // TODO Call via AJAX? In which case return using @RequestBody
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getMilestones() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("milestones", milestoneDAO.findAll());
        return mav;
    }

}
