package org.example.entity;

import org.example.annotation.Publish;

public class Person {
    public String getClassName(){
        return "Person";
    }

    @Publish
    private String firstName;
    @Publish
    private String lastName;
    @Publish
    private String birthdate;

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
