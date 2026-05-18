package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

public class Resident {

    @Getter @Setter
    private Long id;

    /** First name shown to visitors who already know the resident */
    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    /**
     * The room where the resident currently lives.
     * This is a reference to a Destination of type RESIDENT_ROOM.
     */
    @Getter @Setter
    private Destination currentRoom;

    /**
     * Primary contact person (family member / main visitor).
     * Used for pre-visit email and return-visit personalisation.
     */
    @Getter @Setter
    private Visitor primaryContact;

    /** Whether the resident is currently accepting visitors */
    @Getter @Setter
    private boolean acceptingVisitors;

    public Resident(Long id, String firstName, String lastName,
                    Destination currentRoom, Visitor primaryContact,
                    boolean acceptingVisitors) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentRoom = currentRoom;
        this.primaryContact = primaryContact;
        this.acceptingVisitors = acceptingVisitors;
    }

    public Resident() {}


    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", currentRoom=" + (currentRoom != null ? currentRoom.getName() : "none") +
                ", acceptingVisitors=" + acceptingVisitors +
                '}';
    }
}
