package io.andylee.whiteboard.domain;

/**
 *
 */
public class State {

    private String action;

    private String status;

    public State(String action, String status) {
        this.action = action;
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (!action.equals(state.action)) return false;
        if (!status.equals(state.status)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = action.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
