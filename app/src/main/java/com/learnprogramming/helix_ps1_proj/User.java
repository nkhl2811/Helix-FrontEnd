package com.learnprogramming.helix_ps1_proj;

public class User {

    private String userid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String usertype;
    private String phone;
    private String address;

    private boolean approval;
    private String hashcode;
    private boolean notification_flag;

    public User(String userid, String firstname, String lastname, String username, String email, String password, String usertype, String phone, String address, boolean approval, String hashcode, boolean notification_flag) {

        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.phone = phone;
        this.address = address;
        this.approval = approval;
        this.hashcode = hashcode;
        this.notification_flag = notification_flag;
    }

    public String getUserid() {
        return userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public boolean isApproval() {
        return approval;
    }

    public String getHashcode() {
        return hashcode;
    }

    public boolean isNotification_flag() {
        return notification_flag;
    }
}
