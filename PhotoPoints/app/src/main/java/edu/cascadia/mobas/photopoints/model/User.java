package edu.cascadia.mobas.photopoints.model;

import java.util.Date;

public class User {

    private String mFirstName;
    private String mLastName;
    private Date mDateOfBirth;
    private String mEmailAddress;

    public User(String firstName, String lastName, Date dateOfBirth, String emailAddress) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mDateOfBirth = dateOfBirth;
        this.mEmailAddress = emailAddress;
    }
}
