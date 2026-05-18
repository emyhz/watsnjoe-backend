package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

public class HouseRule {

    @Getter @Setter
    private Long id;

    /** Short rule title displayed in bold */
    @Getter @Setter
    private String title;

    /** Explanation / reason shown below the title */
    @Getter @Setter
    private String subtitle;

    /** Display order on the list */
    @Getter @Setter
    private int displayOrder;

    /** Whether this rule is currently active and should be shown */
    @Getter @Setter
    private boolean active;

    public HouseRule(Long id, String title, String subtitle, int displayOrder, boolean active) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.displayOrder = displayOrder;
        this.active = active;
    }

    public HouseRule() {}

    @Override
    public String toString() {
        return "HouseRule{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", displayOrder=" + displayOrder +
                ", active=" + active +
                '}';
    }
}
