package io.andylee.whiteboard.domain;

/**
 *
 */
public class Action {

    private String action;

    private String label;

    private String hints;

    private Object[] inputFields;

    public Action(String action) {
        this.action = action;
        this.label = action;
    }

    public Action(String action, String label, String hints, Object[] inputFields) {
        this.action = action;
        this.label = label;
        this.hints = hints;
        this.inputFields = inputFields;
    }

    public String getAction() {
        return action;
    }

    public String getLabel() {
        return label;
    }

    public String getHints() {
        return hints;
    }

    public Object[] getInputFields() {
        return inputFields;
    }
}
