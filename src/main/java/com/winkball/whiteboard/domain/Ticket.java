package com.winkball.whiteboard.domain;

/**
 * Represents a Ticket or unit of work to be completed.
 * <p>
 * At the moment this is tightly coupled to the attributes of tickets in Trac.
 * </p>
 */
public class Ticket {

    private Integer id;

    private String owner;

    private String type;

    private String milestone;    // TODO switch to com.winkball.whiteboard.domain.Milestone

    private String summary;

    private String status;

    private String priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
