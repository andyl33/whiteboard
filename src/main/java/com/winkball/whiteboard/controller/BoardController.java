package com.winkball.whiteboard.controller;

import java.util.*;

import com.winkball.whiteboard.controller.filter.ColumnFilter;
import com.winkball.whiteboard.data.TicketRepository;
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

import javax.servlet.http.HttpServletRequest;

/**
 * Main Controller for generating the board contents and handling updates.
 */
@Controller
@RequestMapping (value="/whiteboard")
public class BoardController {

    @Autowired
    @Qualifier("TracMilestoneRepository")
    private MilestoneRepository milestoneRepository;

    @Autowired
    @Qualifier("TracTicketRepository")
    private TicketRepository ticketRepository;

    @Autowired
    private ColumnFilter columnFilter;

    @RequestMapping(method = RequestMethod.GET)
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
        mav.addAllObjects(columnFilter.filter(allTickets));

        mav.addObject("gravatarEnabled", false);

        return mav;
    }

    @RequestMapping(value="/update", produces = {"application/json"})
    @ResponseBody
    public String update(@RequestParam(value="newColumn") String column) {
        System.out.println(column);
        return "success";
    }

}
