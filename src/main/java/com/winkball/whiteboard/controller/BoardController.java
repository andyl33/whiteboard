package com.winkball.whiteboard.controller;

import java.util.*;

import com.winkball.whiteboard.controller.filter.ColumnFilter;
import com.winkball.whiteboard.data.TicketRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.winkball.whiteboard.data.MilestoneRepository;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.domain.Ticket;

/**
 * Main Controller for generating the board contents and handling updates.
 */
@Controller
public class BoardController {

    @Autowired
    @Qualifier("TracMilestoneRepository")
    private MilestoneRepository milestoneRepository;

    @Autowired
    @Qualifier("TracTicketRepository")
    private TicketRepository ticketRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/whiteboard")
    public ModelAndView get() {
        // TODO - remember the selected milestone, cookie?, localstorage?

        ModelAndView mav = new ModelAndView("main");

        List<Milestone> milestones = milestoneRepository.findAll();
        Map<String, String> milestoneOptions = new LinkedHashMap<String, String>();
        milestoneOptions.put("", ""); // Empty row. Is there a better way?
        if (milestones != null) {
            for (Milestone milestone : milestones) {
                milestoneOptions.put(milestone.toString(), milestone.toString());
            }
        }
        mav.addObject("milestones", milestoneOptions);

        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView selectMilestone(@RequestParam(value="selectedMilestone", required=true) String milestone) {

        List<Ticket> allTickets = ticketRepository.find(new Milestone(milestone));

        ModelAndView mav = new ModelAndView("board");
        mav.addAllObjects(new ColumnFilter().filter(allTickets));

        mav.addObject("gravatarEnabled", false);

        return mav;
    }

}
