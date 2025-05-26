package io.andylee.whiteboard.domain;

/**
 * Indicates a achievement of significance within the overall process.
 */
public class Milestone {

    private String milestone;

    public Milestone(String milestone) {
        this.milestone = milestone;
    }

    public String toString() {
        return milestone;
    }
}
