package com.learnprogramming.helix_ps1_proj;

import java.util.Calendar;

public class Comment {
    private String username;
    private String commentText;
    private Calendar commentCalendar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Calendar getCommentCalendar() {
        return commentCalendar;
    }

    public void setCommentCalendar(Calendar commentCalendar) {
        this.commentCalendar = commentCalendar;
    }
}
