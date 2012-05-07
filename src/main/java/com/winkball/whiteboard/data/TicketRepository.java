package com.winkball.whiteboard.data;

import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.domain.Ticket;

import java.util.List;

public interface TicketRepository {

   Ticket find(int id);

   List<Ticket> find(Milestone milestone);

   void create(Ticket ticket);

   Ticket update(Ticket ticket, String comment, String author);

}
