package com.winkball.whiteboard.controller;

import java.util.*;

import com.winkball.whiteboard.data.TicketRepository;
import com.winkball.whiteboard.domain.*;
import com.winkball.whiteboard.domain.configuration.BoardConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.winkball.whiteboard.data.MilestoneRepository;

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
    private BoardConfiguration boardConfiguration;

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
    public ModelAndView selectMilestone(@RequestParam(value="selectedMilestone", required=true) String milestone, WebRequest request) {
        // TODO check board exists in session map first?
        TicketBoard ticketBoard = new TracTicketBoard(boardConfiguration, ticketRepository); // TODO auto inject dependencies
        ticketBoard.initialise(new Milestone(milestone));

        @SuppressWarnings("unchecked")
        Map<Milestone, TicketBoard> boards = (Map<Milestone, TicketBoard>) request.getAttribute("boards", RequestAttributes.SCOPE_SESSION);
        if (boards == null) {
            boards = new HashMap<Milestone, TicketBoard>();
        }
        boards.put(new Milestone(milestone), ticketBoard); // TODO replace board if key exists?
        request.setAttribute("boards", boards, RequestAttributes.SCOPE_SESSION);

        ModelAndView mav = new ModelAndView("board");
        mav.addObject("columns", ticketBoard.getColumns()); // TODO change board.ftl to generate columns markup on the fly

        mav.addObject("gravatarEnabled", false);

        return mav;
    }

    @RequestMapping(value="/update", produces = {"application/json"})
    @ResponseBody
    public String update(@RequestParam(value="destination") String destination,
                         @RequestParam(value="origin") String origin,
                         @RequestParam(value="ticket_number") Integer number,
                         @RequestParam(value="ticket_owner") String owner,
                         @RequestParam(value="ticket_milestone") String milestone) {

        // TODO perform validation on inputs

        // TODO Prompt for user when moving to review/inprogress
        // TODO Prompt for release version when moving to testing

        // Load a fresh copy of the ticket to ensure we don't collide with other updates.
        Ticket ticketToUpdate = ticketRepository.find(number);

        // TODO Transition as request param - jackson
        Transition requestedTransition = new Transition(origin, destination);


        // TODO users for each transition type (backlog, release_pool), resolution(fixed)?

        ticketToUpdate.apply(requestedTransition);

        // TODO sort out comment generation + owner/author
        ticketRepository.update(ticketToUpdate,
                ticketToUpdate.getType() + " #" + ticketToUpdate.getId() + " " + ticketToUpdate.getAction() + " by " + owner, owner );

        // if "to do" set ticket status = new, owner = backlog
        // if "inprogress" set ticket status = assigned, owner = logged in user
        // if "reviewing" display list of possible reviewers
        //     -- select reviewer
        //     -- change owner to reviewer, ticket status = reviewing
        // if "testing" set ticket status = testing, owner = release pool
        //     -- prompt for released in version

        return "success";
    }

}
