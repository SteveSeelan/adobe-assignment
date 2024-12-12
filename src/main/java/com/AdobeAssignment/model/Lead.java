package com.AdobeAssignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;

public class Lead {
    private String _id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssxxx", timezone = "UTC")
    private OffsetDateTime entryDate;

    public Lead() {
    }

    public Lead(String _id, String email, String firstName, String lastName, String address, OffsetDateTime entryDate) {
        this._id = _id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.entryDate = entryDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OffsetDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(OffsetDateTime entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return "Lead{" + '\n' +
                "id='" + _id + "," + '\n' +
                "email='" + email + "," + '\n' +
                "firstName='" + firstName + "," + '\n' +
                "lastName='" + lastName + "," + '\n' +
                "address='" + address + "," + '\n' +
                "entryDate=" + entryDate + '\n' +
                '}';
    }
}
