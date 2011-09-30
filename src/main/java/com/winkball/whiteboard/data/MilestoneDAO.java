package com.winkball.whiteboard.data;

import com.winkball.whiteboard.domain.Milestone;

import java.util.List;

public interface MilestoneDAO {

    List<Milestone> findAll();

}
