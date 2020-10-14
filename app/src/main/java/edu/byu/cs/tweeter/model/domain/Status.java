package edu.byu.cs.tweeter.model.domain;

import java.time.Instant;

public class Status {
    private User author;
    private Instant timeOfAuthorship;
    private CharSequence text;

    /**
     * Constructor for a Status
     * @param author the user who authored this status
     * @param timeOfAuthorship the moment in time that this status was authored
     * @param text the text of the status
     */
    public Status(User author, Instant timeOfAuthorship, CharSequence text) {
        this.author = author;
        this.timeOfAuthorship = timeOfAuthorship;
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public Instant getTimeOfAuthorship() {
        return timeOfAuthorship;
    }

    public CharSequence getText() {
        return text;
    }
}
