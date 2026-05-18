package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.RouteMatcher;

import java.time.LocalDateTime;

public class PreVisit {

    @Getter @Setter
    private Long id;

    /** The visitor this email was sent to */
    @Getter @Setter
    private Visitor recipient;

    /** The resident they are coming to visit */
    @Getter @Setter
    private Resident resident;

    /** The destination / room for the visit */
    @Getter @Setter
    private Destination destination;

    /** Route already prepared so the visitor can navigate on arrival */
    @Getter @Setter
    private RouteMatcher.Route route;

    /** When the email was sent */
    @Getter @Setter
    private LocalDateTime sentAt;

    /** Optional: scheduled visit date and time */
    @Getter @Setter
    private LocalDateTime scheduledVisitTime;

    /** Optional: custom personal note added by care staff */
    @Getter @Setter
    private String customNote;

    /** Unique deep-link token so visitor can open the route on their phone */
    @Getter @Setter
    private String deepLinkToken;

    /** Language the email was generated in */
    @Getter @Setter
    private String languageCode;

    public PreVisit(Long id, Visitor recipient, Resident resident,
                         Destination destination, RouteMatcher.Route route,
                         LocalDateTime sentAt, LocalDateTime scheduledVisitTime,
                         String customNote, String deepLinkToken, String languageCode) {
        this.id = id;
        this.recipient = recipient;
        this.resident = resident;
        this.destination = destination;
        this.route = route;
        this.sentAt = sentAt;
        this.scheduledVisitTime = scheduledVisitTime;
        this.customNote = customNote;
        this.deepLinkToken = deepLinkToken;
        this.languageCode = languageCode;
    }

    public PreVisit() {}

    @Override
    public String toString() {
        return "PreVisitEmail{" +
                "id=" + id +
                ", recipient=" + (recipient != null ? recipient.getDisplayName() : "none") +
                ", resident=" + (resident != null ? resident.getDisplayName() : "none") +
                ", sentAt=" + sentAt +
                ", scheduledVisitTime=" + scheduledVisitTime +
                ", languageCode='" + languageCode + '\'' +
                '}';
    }
}
