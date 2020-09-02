package com.learnprogramming.helix_ps1_proj;

public class Event {
    private boolean evt_deleted;
    private String evt_id;
    private String evt_desc;
    private String userid;
    private String evt_name;
    private String evt_date;
    private String evt_time;
    private String evt_genre;
    private String evt_img_link;

    public Event(boolean evt_deleted, String evt_id, String evt_desc, String userid, String evt_name, String evt_date, String evt_time, String evt_genre, String evt_img_link) {
        this.evt_deleted = evt_deleted;
        this.evt_id = evt_id;
        this.evt_desc = evt_desc;
        this.userid = userid;
        this.evt_name = evt_name;
        this.evt_date = evt_date;
        this.evt_time = evt_time;
        this.evt_genre = evt_genre;
        this.evt_img_link = evt_img_link;
    }

    public boolean isEvt_deleted() {
        return evt_deleted;
    }

    public String getEvt_id() {
        return evt_id;
    }

    public String getEvt_desc() {
        return evt_desc;
    }

    public String getUserid() {
        return userid;
    }

    public String getEvt_name() {
        return evt_name;
    }

    public String getEvt_date() {
        return evt_date;
    }

    public String getEvt_time() {
        return evt_time;
    }

    public String getEvt_genre() {
        return evt_genre;
    }

    public String getEvt_img_link() {
        return evt_img_link;
    }
}
