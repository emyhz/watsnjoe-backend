package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

public class Visitor {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String phoneNumber;
    @Getter @Setter
    private Locale preferredLanguage;


    @Getter @Setter
    private Resident primaryResident;

    public Visitor(Long id, String firstName, String lastName,
                   String email, String phoneNumber,
                   Locale preferredLanguage, Resident primaryResident) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.primaryResident = primaryResident;
    }

    public Visitor() {}

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", preferredLanguage=" + (preferredLanguage != null ? preferredLanguage.getLanguage() : "nl") +
                '}';
    }
}
