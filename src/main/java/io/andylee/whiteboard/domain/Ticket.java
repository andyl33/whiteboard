package io.andylee.whiteboard.domain;

import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Ticket or unit of work to be completed.
 * <p>
 * At the moment this is tightly coupled to the attributes of tickets in Trac.
 * </p>
 */
public class Ticket {

    // A map of transitions (origin->destination) to final ticket system states.
    // A null final state indicates the transition is not supported.
    private static final Map<Transition, State> STATE_TRANSITIONS = new HashMap<Transition, State>();

    static {
        STATE_TRANSITIONS.put(new Transition("todo", "inprogress"), new State("accept", "assigned"));
        STATE_TRANSITIONS.put(new Transition("todo", "reviewing"), null);
        STATE_TRANSITIONS.put(new Transition("todo", "testing"), null);
        STATE_TRANSITIONS.put(new Transition("todo", "done"), null);
        STATE_TRANSITIONS.put(new Transition("inprogress", "todo"), new State("reassign", "new"));
        STATE_TRANSITIONS.put(new Transition("inprogress", "reviewing"), new State("review", "reviewing"));
        STATE_TRANSITIONS.put(new Transition("inprogress", "testing"), null);
        STATE_TRANSITIONS.put(new Transition("inprogress", "done"), null);
        STATE_TRANSITIONS.put(new Transition("reviewing", "todo"), new State("reassign", "new"));
        STATE_TRANSITIONS.put(new Transition("reviewing", "inprogress"), new State("fail_review", "assigned"));
        STATE_TRANSITIONS.put(new Transition("reviewing", "testing"), new State("testing", "testing"));
        STATE_TRANSITIONS.put(new Transition("reviewing", "done"), new State("resolve", "closed"));
        STATE_TRANSITIONS.put(new Transition("testing", "todo"), new State("reject", "failed_testing"));
        STATE_TRANSITIONS.put(new Transition("testing", "inprogress"), null);
        STATE_TRANSITIONS.put(new Transition("testing", "reviewing"), null);
        STATE_TRANSITIONS.put(new Transition("testing", "done"), new State("resolve", "closed"));
    }

    private Integer id;

    private String action;

    private String releaseVersion;

    private String version;

    private String owner;

    private String type;

    private String milestone;    // TODO switch to io.andylee.whiteboard.domain.Milestone

    private String summary;

    private String status;

    private String priority;

    public Ticket(Integer id, String status) {
        this.status = status;
    }

    public Ticket() {}

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    // ~ Lifecycle methods ============================================================================================

    public void apply(Transition transition) throws InvalidStateException, UnknownColumnException {
        Validate.notNull(transition, "Transition initiating a state change cannot be null");

        if (STATE_TRANSITIONS.containsKey(transition)) {
            State state = STATE_TRANSITIONS.get(transition);
            if (state == null) {
                throw new InvalidStateException("State change is not supported for: " + transition);
            }
            // transition ok. update status and action
            this.action = state.getAction();
            this.status = state.getStatus();
        } else {
            throw new UnknownColumnException(transition + " support is not currently configured.");
        }
    }
}
