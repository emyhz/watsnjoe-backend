package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

public class NavigationStep {

    @Getter @Setter
    private Long id;

    /** Position in the route, starting at 1 */
    @Getter @Setter
    private int stepNumber;

    /**
     * Direction arrow symbol shown prominently.
     * E.g. "→", "↑", "←", "★" (arrived)
     */
    @Getter @Setter
    private String directionArrow;

    /** Short action title */
    @Getter @Setter
    private String title;

    /** Supplementary hint */
    @Getter @Setter
    private String hint;

    /** Distance / landmark label shown in corner */
    @Getter @Setter
    private String distanceLabel;


    public NavigationStep(Long id, int stepNumber, String directionArrow,
                          String title, String hint, String distanceLabel) {
        this.id = id;
        this.stepNumber = stepNumber;
        this.directionArrow = directionArrow;
        this.title = title;
        this.hint = hint;
        this.distanceLabel = distanceLabel;
    }

    public NavigationStep() {}

    @Override
    public String toString() {
        return "NavigationStep{" +
                "id=" + id +
                ", stepNumber=" + stepNumber +
                ", directionArrow='" + directionArrow + '\'' +
                ", title='" + title + '\'' +
                ", hint='" + hint + '\'' +
                ", distanceLabel='" + distanceLabel + '\'' +
                '}';
    }
}
