package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;
import nl.watsnjoe.s5.watsnjoebackend.domain.enums.DestinationType;

public class Facility {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private DestinationType type;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Destination destination;
    @Getter @Setter
    private String openingHours;
    @Getter @Setter
    private boolean isOpen;
    @Getter @Setter
    private int floor;

    public Facility(Long id, String name, DestinationType type, String description, Destination destination, String openingHours, boolean isOpen, int floor) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.destination = destination;
        this.openingHours = openingHours;
        this.isOpen = isOpen;
        this.floor = floor;
    }

    public Facility() {}

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", locationDescription='" + description + '\'' +
                '}';
    }
}
