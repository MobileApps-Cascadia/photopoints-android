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

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public Date getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.mDateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setmEmailAddress(String emailAddress) {
        this.mEmailAddress = emailAddress;
    }
}
