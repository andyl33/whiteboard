package com.winkball.whiteboard.data.trac;

import com.winkball.whiteboard.data.TicketRepository;
import com.winkball.whiteboard.domain.Action;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.domain.Ticket;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.*;
import com.winkball.whiteboard.webservice.xmlrpc.RemoteProcedureCallException;
import com.winkball.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Implementation of the {@link com.winkball.whiteboard.data.TicketRepository} which retrieves Ticket information
 * from a remote Trac installation using XML-RPC
 */
@Repository(value="TracTicketRepository")
public class TracTicketRepository implements TicketRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TracTicketRepository.class);

    private XmlRpcTemplate xmlRpcTemplate;

    @Autowired
    public TracTicketRepository(XmlRpcTemplate xmlRpcTemplate) {
        this.xmlRpcTemplate = xmlRpcTemplate;
    }

    public Ticket find(int id) {
        Ticket ticket = xmlRpcTemplate.callForObject(new GetTicket(id));
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket with Id " + id + " does not exist in Trac");
        }
        ticket.setId(id); // we have to set the id here as Trac does not return it.
        return ticket;
    }

    public List<Ticket> find(Milestone milestone) {
        List<Ticket> ticketsByMilestone = null;
        try {

            // The Trac XML-RPC API only returns ticket ids as the result of a query, so we need to get these first
            List<Integer> ticketIdsInMilestone = xmlRpcTemplate.callForList(new QueryForTicketIds("milestone=" +
                    milestone.toString()));

            // Now get the tickets - note we use a multicall here to reduce network roundtrips per ticket request
            ticketsByMilestone = xmlRpcTemplate.callForList(new GetTickets(ticketIdsInMilestone));

        } catch (RemoteProcedureCallException rpce) {
            LOGGER.info("No tickets found matching milestone {}", milestone);
        }

        return ticketsByMilestone;
    }

    public void create(Ticket ticket) {

    }

    public Ticket update(Ticket ticket, String comment, String author) {
        
        List<Action> actions = xmlRpcTemplate.callForList(new GetActionsForTicket(ticket.getId()));

        Map<String, Object> changes = new HashMap<String, Object> ();
        changes.put("status", ticket.getStatus());
        changes.put("action", ticket.getAction());
        changes.put("_ts", ticket.getVersion()); // A token used to determine if the ticket is updated while we do this
        changes.put("owner", ticket.getOwner());

        UpdateTicket updateTicketCall = new UpdateTicket(ticket.getId(), comment, changes, author);

        return xmlRpcTemplate.callForObject(updateTicketCall);

    }

}
