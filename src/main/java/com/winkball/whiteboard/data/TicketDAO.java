package com.winkball.whiteboard.data;

import com.winkball.whiteboard.domain.Milestone;
import com.winkball.whiteboard.domain.Ticket;

import java.util.List;

public interface TicketDAO {

   Ticket find(int id);

   List<Ticket> find(Milestone milestone);

   void store(Ticket ticket);

   void store(List<Ticket> tickets);
}
