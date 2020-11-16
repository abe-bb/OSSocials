package edu.byu.cs.tweeter.model.domain;

import java.time.Instant;
import java.util.Objects;

public class Status {
    private User author;
    private Instant timeOfAuthorship;
    private String text;

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTimeOfAuthorship(Instant timeOfAuthorship) {
        this.timeOfAuthorship = timeOfAuthorship;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Constructor for a Status
     * @param author the user who authored this status
     * @param timeOfAuthorship the moment in time that this status was authored
     * @param text the text of the status
     */
    public Status(User author, Instant timeOfAuthorship, String text) {
        this.author = author;
        this.timeOfAuthorship = timeOfAuthorship;
        this.text = text;
    }

    public Status() {
        author = null;
        timeOfAuthorship = null;
        text = null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(author, status.author) &&
                Objects.equals(timeOfAuthorship, status.timeOfAuthorship) &&
                Objects.equals(text, status.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, timeOfAuthorship, text);
    }
}
