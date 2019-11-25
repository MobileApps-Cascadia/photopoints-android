package edu.cascadia.mobas.photopoints.data.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity (tableName = "Users")
public class DBUser {

    @PrimaryKey
    @ColumnInfo(name = "EmailAddress")
    @NonNull
    private String EmailAddress;

    @ColumnInfo(name = "FirstName")
    private String FirstName;

    @ColumnInfo(name = "LastName")
    private String LastName;

    @ColumnInfo(name = "DateOfBirth")
    private Date DateOfBirth;

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}
