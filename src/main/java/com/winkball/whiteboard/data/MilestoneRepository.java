package io.andylee.whiteboard.data;

import io.andylee.whiteboard.domain.Milestone;

import java.util.List;

public interface MilestoneRepository {

    List<Milestone> findAll();

}
