package com.learnprogramming.helix_ps1_proj;

public class CreateEventResponse {
    private String message;
    private Event event;

    public CreateEventResponse(String message, Event event) {
        this.message = message;
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public Event getEvent() {
        return event;
    }
}
