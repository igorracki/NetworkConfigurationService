package com.igor.ns.event;

public class CachePurgeEvent {

    private final String message;

    public CachePurgeEvent(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
