package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

public class Visitor {

    @Getter @Setter
    private Long id;

    /** Display name used in the personalised phone screen, e.g. "Hallo Anja" */
    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    /** Contact e-mail for pre-visit emails */
    @Getter @Setter
    private String email;

    /** Phone number for SMS / QR follow-up */
    @Getter @Setter
    private String phoneNumber;

    /**
     * Preferred language for kiosk and email content.
     */
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
