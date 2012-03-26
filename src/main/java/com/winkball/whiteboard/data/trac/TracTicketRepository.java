package com.winkball.whiteboard.data.trac;

import com.winkball.whiteboard.data.TicketRepository;
import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.domain.Ticket;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.GetTicket;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.GetTickets;
import com.winkball.whiteboard.webservice.xmlrpc.calls.trac.QueryForTicketIds;
import com.winkball.whiteboard.webservice.xmlrpc.RemoteProcedureCallException;
import com.winkball.whiteboard.webservice.xmlrpc.XmlRpcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
        return null;
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

    public void store(Ticket ticket) {

    }

    public void store(List<Ticket> tickets) {

    }
}
