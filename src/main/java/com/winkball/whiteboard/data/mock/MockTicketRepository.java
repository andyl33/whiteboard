package io.andylee.whiteboard.data.mock;

import io.andylee.whiteboard.data.TicketRepository;
import io.andylee.whiteboard.domain.Milestone;
import io.andylee.whiteboard.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value="MockTicketRepository")
public class MockTicketRepository implements TicketRepository {
    
    private static final List<Ticket> tickets = new ArrayList<Ticket>();
    
    static {
        for(int i = 1; i < 11; i++) {
            Ticket ticket = new Ticket();
            ticket.setId(i);
            ticket.setMilestone("Milestone");
            ticket.setOwner("Backlog");
            ticket.setPriority("High");
            ticket.setStatus("New");
            ticket.setSummary("A ticket Summary");
            ticket.setType("defect");
            tickets.add(ticket);
        }
    }

    public Ticket find(int id) {
        Ticket ticket = tickets.get(id);
        if (ticket == null) {
            throw new RuntimeException("Ticket Not Found.");
        }
        return ticket;
    }

    public List<Ticket> find(Milestone milestone) {
        return tickets;
    }

    public void create(Ticket ticket) {
        
        if (ticket.getId() == null) {
            // New ticket
            ticket.setId(tickets.size() + 1);
            tickets.add(ticket);
        } else {
            // Update. Well in this case we just remove the old and drop in the new at the same index.
            Ticket target = tickets.get(ticket.getId());
            if (target != null && target.getId() != null) {
                tickets.set(ticket.getId(), ticket);
            } else {
                throw new RuntimeException("Unknown ticket error. Could not update ticket at index " + ticket.getId());
            }
        }
    }

    public void create(List<Ticket> tickets) {
        for (Ticket ticket: tickets) {
            create(ticket);
        }
    }

    @Override
    public Ticket update(Ticket ticket, String comment, String author) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
