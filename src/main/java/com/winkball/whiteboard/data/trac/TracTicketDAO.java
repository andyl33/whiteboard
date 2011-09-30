package com.winkball.whiteboard.data.trac;

import com.winkball.whiteboard.data.TicketDAO;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.domain.Ticket;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.GetTicket;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.QueryForTicketIds;
import com.winkball.whiteboard.webservice.xmlrpc.RemoteProcedureCallException;
import com.winkball.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link TicketDAO} which retrieves Ticket information
 * from a remote Trac installation using XML-RPC
 */
@Component
public class TracTicketDAO implements TicketDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TracTicketDAO.class);

    private XmlRpcTemplate xmlRpcTemplate;

    @Autowired
    public TracTicketDAO(XmlRpcTemplate xmlRpcTemplate) {
        this.xmlRpcTemplate = xmlRpcTemplate;
    }

    public Ticket find(int id) {
        return null;
    }

    public List<Ticket> find(Milestone milestone) {
        List<Ticket> ticketsByMilestone = null;
        try {
            // The Trac XML-RPC API only returns ticket ids as the result of a query, so we need to get these first
            List<Integer> ticketIdsByMilestone = xmlRpcTemplate.callForList(new QueryForTicketIds("milestone=" +
                    milestone.toString()));

            // Now loop through the ids and get the ticket data for each ticket.
            if (!CollectionUtils.isEmpty(ticketIdsByMilestone)) {
                ticketsByMilestone = new ArrayList<Ticket>();

                for (Integer ticketId : ticketIdsByMilestone) {
                    Ticket ticket = xmlRpcTemplate.callForObject(new GetTicket(ticketId));
                    if (ticket != null) {
                        ticket.setId(ticketId); // GetTicket call doesn't return the Id so add it here.
                        ticketsByMilestone.add(ticket);
                    }
                }
            }

        } catch (RemoteProcedureCallException rpce) {
            LOGGER.info("No tickets found matching milestone {}", milestone);
        }

        return ticketsByMilestone;
    }

    public void store(Ticket ticket) {

    }

    public void store(List<Ticket> tickets) {

    }
}
