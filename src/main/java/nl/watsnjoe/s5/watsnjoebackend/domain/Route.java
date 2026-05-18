package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Route {

    @Getter @Setter
    private Long id;

    /** The destination this route leads to */
    @Getter @Setter
    private Destination destination;

    /** Ordered list of steps to follow */
    @Getter @Setter
    private List<NavigationStep> steps = new ArrayList<>();

    /**
     * Total estimated walking time in minutes.
     * Shown as "±3 min lopen" in the prototype.
     */
    @Getter @Setter
    private int estimatedMinutes;

    /**
     * QR code token linking to the mobile version of this route.
     * Expires after validityMinutes.
     */
    @Getter @Setter
    private String qrToken;

    /** When the QR code / route was generated */
    @Getter @Setter
    private LocalDateTime generatedAt;

    /** How many minutes the QR code remains valid (60 in the prototype) */
    @Getter @Setter
    private int validityMinutes;

    /** Whether this is a stair-free / elevator route */
    @Getter @Setter
    private boolean elevatorRoute;

    public Route(Long id, Destination destination, List<NavigationStep> steps,
                 int estimatedMinutes, String qrToken,
                 LocalDateTime generatedAt, int validityMinutes,
                 boolean elevatorRoute) {
        this.id = id;
        this.destination = destination;
        this.steps = steps;
        this.estimatedMinutes = estimatedMinutes;
        this.qrToken = qrToken;
        this.generatedAt = generatedAt;
        this.validityMinutes = validityMinutes;
        this.elevatorRoute = elevatorRoute;
    }

    public Route() {}

    /** Convenience: total number of steps */
    public int getTotalSteps() {
        return steps != null ? steps.size() : 0;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", destination=" + (destination != null ? destination.getName() : "none") +
                ", totalSteps=" + getTotalSteps() +
                ", estimatedMinutes=" + estimatedMinutes +
                ", qrToken='" + qrToken + '\'' +
                ", validityMinutes=" + validityMinutes +
                ", elevatorRoute=" + elevatorRoute +
                '}';
    }
}
