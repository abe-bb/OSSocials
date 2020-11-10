package edu.cs.byu.tweeter.shared.response;

import java.util.Objects;

/**
 * A response that can indicate whether there is more data available from the server.
 */
public class PagedResponse extends Response {

    private final boolean hasMorePages;

    PagedResponse(boolean success, boolean hasMorePages) {
        super(success);
        this.hasMorePages = hasMorePages;
    }

    PagedResponse(boolean success, String message, boolean hasMorePages) {
        super(success, message);
        this.hasMorePages = hasMorePages;
    }

    /**
     * An indicator of whether more data is available from the server. A value of true indicates
     * that the result was limited by a maximum value in the request and an additional request
     * would return additional data.
     *
     * @return true if more data is available; otherwise, false.
     */
    public boolean getHasMorePages() {
        return hasMorePages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagedResponse that = (PagedResponse) o;
        return hasMorePages == that.hasMorePages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasMorePages);
    }
}
