package edu.byu.cs.tweeter.model.domain;

import java.time.Instant;
import java.util.Objects;

public class Status {
    private User author;
    private String timeOfCreation;
    private String text;

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTimeOfCreation(Instant timeOfCreation) {
        this.timeOfCreation = timeOfCreation.toString();
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Constructor for a Status
     * @param author the user who authored this status
     * @param timeOfCreation the moment in time that this status was authored
     * @param text the text of the status
     */
    public Status(User author, Instant timeOfCreation, String text) {
        this.author = author;
        this.timeOfCreation = timeOfCreation.toString();
        this.text = text;
    }

    public Status() {
        author = null;
        timeOfCreation = null;
        text = null;
    }

    public void setTimeOfCreation(String timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public User getAuthor() {
        return author;
    }

    public String getTimeOfCreation() {
        return timeOfCreation;
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
                Objects.equals(timeOfCreation, status.timeOfCreation) &&
                Objects.equals(text, status.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, timeOfCreation, text);
    }
}
