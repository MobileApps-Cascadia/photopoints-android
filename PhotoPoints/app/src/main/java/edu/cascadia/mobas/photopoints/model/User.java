package edu.cascadia.mobas.photopoints.model;

import java.util.Date;

public class User {

    private int mUserID;
    private String mFirstName;
    private String mLastName;
    private Date mDateOfBirth;
    private String mEmailAddress;

    public User(int userID, String firstName, String lastName, Date dateOfBirth, String emailAddress) {
        this.mUserID = userID;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mDateOfBirth = dateOfBirth;
        this.mEmailAddress = emailAddress;
    }

    //<editor-fold desc="Getters and setters">
    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public Date getmDateOfBirth() {
        return mDateOfBirth;
    }

    public void setmDateOfBirth(Date mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public String getmEmailAddress() {
        return mEmailAddress;
    }

    public void setmEmailAddress(String mEmailAddress) {
        this.mEmailAddress = mEmailAddress;
    }
    //</editor-fold>
}
